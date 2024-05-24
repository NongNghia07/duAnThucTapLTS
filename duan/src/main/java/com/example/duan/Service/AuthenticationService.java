package com.example.duan.Service;

import com.example.duan.DTO.request.IntrospectRequest;
import com.example.duan.DTO.request.LoginRequest;
import com.example.duan.DTO.request.LogoutRequest;
import com.example.duan.DTO.request.RefreshRequest;
import com.example.duan.DTO.response.AuthenticationResponse;
import com.example.duan.DTO.response.IntrospectResponse;
import com.example.duan.Entity.InvalidatedToken;
import com.example.duan.Entity.User;
import com.example.duan.Exception.AppException;
import com.example.duan.Exception.ErrorCode;
import com.example.duan.Repository.InvalidatedTokenRepository;
import com.example.duan.Repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    InvalidatedTokenRepository invalidatedTokenRepository;
    @NonFinal
    protected static final String SIGNER_KEY = "5f+h+PejWfWCZFwneTq4W5nzVt/x4oev3S0C9As/b4fRNs/DuO9G65OuoLsj3cy8";

    public IntrospectResponse instrospect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        boolean inValid = true;
        try {
            verifyToken(token);
        }
        catch (Exception e){
            inValid = false;
        }
        return IntrospectResponse.builder()
                .valid(inValid)
                .build();



    }
    public AuthenticationResponse authenticate(LoginRequest request){
        var user =  userRepository.findByUserName(request.getUserName())
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if(!authenticated)
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        var token = createToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();

    }

    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier jwsVerifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expired = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(jwsVerifier);
        if(!(verified && expired.after(new Date())))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        if(invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new AppException(ErrorCode.UNAUTHENTICATED);


        return signedJWT;
    }

    private String createToken(User user){
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        Instant expirationTime = Instant.now().plusSeconds(3600);
        Date expirationDate = Date.from(expirationTime);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUserName())
                .issuer("anhduc")
                .issueTime(new Date())
                .expirationTime(expirationDate)
                .jwtID(UUID.randomUUID().toString())
                .claim("scope",buildScope(user))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildScope(User user){
        StringJoiner stringJoiner = new StringJoiner(" ");
        if(!CollectionUtils.isEmpty(user.getRoles())){
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getRoleName());
                if(!CollectionUtils.isEmpty(role.getPermissions())){
                    role.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
                }

            });
        }
        return stringJoiner.toString();
    }

    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        var signToken = verifyToken(request.getToken());
        String jit = signToken.getJWTClaimsSet().getJWTID();
        Date expiredTime = signToken.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jit)
                .expriedTime(expiredTime)
                .build();

        invalidatedTokenRepository.save(invalidatedToken);
    }

    public AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException {
        var signedJWT = verifyToken(request.getToken());

        var jit = signedJWT.getJWTClaimsSet().getJWTID();
        var expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken =
                InvalidatedToken.builder()
                        .id(jit)
                        .expriedTime(expiryTime)
                        .build();

        invalidatedTokenRepository.save(invalidatedToken);

        var username = signedJWT.getJWTClaimsSet().getSubject();

        var user =
                userRepository.findByUserName(username).orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        var token = createToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }
}
