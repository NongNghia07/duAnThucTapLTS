package com.example.duan.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WardDTO {
    private int id;
    @NotBlank(message = "Name can't not be null")
    private String name;
    private DistrictDTO district;
    private Set<UserDTO> users;
}
