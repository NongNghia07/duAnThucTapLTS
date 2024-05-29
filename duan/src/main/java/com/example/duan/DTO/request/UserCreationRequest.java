package com.example.duan.DTO.request;

import com.example.duan.Enum.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    String userName;
    @Size(min = 8,message = "PASSWORD_INVALID")
    String password;
//    String fullName;
//    Date dateOfBirth;
//    String address;
//    String avatar;
//    @Email
//    String email;
    Set<String> roles;

}
