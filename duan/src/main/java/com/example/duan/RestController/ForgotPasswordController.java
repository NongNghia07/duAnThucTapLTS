package com.example.duan.RestController;

import com.example.duan.DTO.request.ChangePasswordRequest;
import com.example.duan.DTO.response.ApiResponse;
import com.example.duan.Service.ForgotPasswordService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/forgotpassword")
public class ForgotPasswordController {
    ForgotPasswordService forgotPasswordService;

    //    @PostMapping("/verifymail/{email}")
    @PostMapping("/verifymail")
    public ResponseEntity<ApiResponse> verifyEmail(@RequestParam String email){
        return ResponseEntity.ok().body(
                ApiResponse.<String>builder()
                        .result(forgotPasswordService.forgotPassword(email))
                        .build()
        );

    }

    //    @PostMapping("verifyotp/{otp}/{email}")
    @PostMapping("verifyotp")
    public ResponseEntity<ApiResponse> verifyOtp(@RequestParam Integer otp,String email){
        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .result(forgotPasswordService.verifyOTP(otp,email))
                        .build()
        );
    }

        @PostMapping("changepassword/{email}")
    public ResponseEntity<ApiResponse> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest,
                                                      @PathVariable String email){
        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .result(forgotPasswordService.changePassword(changePasswordRequest,email))
                        .build()
        );
    }
}
