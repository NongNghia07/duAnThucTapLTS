package com.example.duan.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubjectDetailDTO {
    private int id;
    private String name;
    private boolean isFinished = false;
    private boolean isActive = false;
    private String linkVideo;
    private SubjectDTO subject;
}
