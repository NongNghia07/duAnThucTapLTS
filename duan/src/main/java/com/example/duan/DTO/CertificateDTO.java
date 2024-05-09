package com.example.duan.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CertificateDTO {
    private int id;
    private String name;
    private String image;
    private String description;
    private int certificateTypeId; // ID của CertificateType
}
