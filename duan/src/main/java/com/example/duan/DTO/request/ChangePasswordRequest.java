package com.example.duan.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ChangePasswordRequest {
    String password;
    String repeatPassword;

}
