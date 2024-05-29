package com.example.duan.DTO;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;

//
////import jakarta.validation.constraints.NotBlank;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.Set;
//
//@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private int id;

////    @NotBlank(message = "userName can't be null")
  private String userName;
//    private String password;
//
//    private ProvinceDTO province;
//
  private Set<PermissionDTO> permissions;
}
