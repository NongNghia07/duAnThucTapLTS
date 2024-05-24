package com.example.duan.DTO.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    int id;
    String userName;
//    String fullName;
//    Date dateOfBirth;
//    String address;
//    String avatar;
//    String email;
    Set<RoleResponse> roles;
}
