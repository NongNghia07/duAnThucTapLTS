package com.example.duan.Service;

import com.example.duan.DTO.request.ChangePasswordRequest;
import com.example.duan.DTO.request.MailBodyRequest;
import com.example.duan.Entity.ForgotPassword;
import com.example.duan.Entity.User;
import com.example.duan.Repository.ForgotPasswordRepository;
import com.example.duan.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ForgotPasswordService {
    JavaMailSender javaMailSender;
    UserRepository userRepository;
    ForgotPasswordRepository forgotPasswordRepository;
    PasswordEncoder passwordEncoder;

    public void sendSimpleMessage(MailBodyRequest mailBody){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(message.getTo());
        message.setFrom("nghia270902@gmail.com");
        message.setSubject(mailBody.getSubject());
        message.setText(mailBody.getText());

        javaMailSender.send(message);

    }

    public String forgotPassword(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Please provide an valid email"));

        Integer otp = generateOTP();
        MailBodyRequest mailBodyRequest = MailBodyRequest.builder()
                .to(email)
                .subject("OTP for Forgot Password request")
                .text("This is the OTP for your Forgot Password request: " +otp)
                .build();

        ForgotPassword forgotPassword = ForgotPassword.builder()
                .otp(otp)
                .exprirationTime(new Date(System.currentTimeMillis() + 60 * 1000))
                .user(user)
                .build();

        sendSimpleMessage(mailBodyRequest);
        forgotPasswordRepository.save(forgotPassword);

        return "Email sent for verification";

    }

    public String verifyOTP(Integer otp,String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Please provide an valid email"));

        ForgotPassword fp = forgotPasswordRepository.findByOtpAndUser(otp,user)
                .orElseThrow(()-> new RuntimeException("Invalid OTP"));
        if(fp.getExprirationTime().before(Date.from(Instant.now()))){
            forgotPasswordRepository.deleteById(fp.getFpid());
            return "OTP has exprired";
        }
        return "OTP verifired";

    }

    public String changePassword(ChangePasswordRequest changePasswordRequest,String email){

        if(!Objects.equals(changePasswordRequest.getPassword(),changePasswordRequest.getRepeatPassword())){
            return "Please enter the password again!";
        }
        String encodePassword = passwordEncoder.encode(changePasswordRequest.getPassword());
        userRepository.updatePassword(email,encodePassword);
        return "Password has been changed!";
    }


    private Integer generateOTP(){
        return ThreadLocalRandom.current().nextInt(100000,999999);
    }
}

