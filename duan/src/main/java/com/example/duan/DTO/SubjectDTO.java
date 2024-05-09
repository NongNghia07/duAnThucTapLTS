package com.example.duan.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubjectDTO {
    private int id;
    private String name;
    private String symbol;
    private boolean isActive = false;
    private Set<SubjectDetailDTO> subjectDetails;
    private Set<CourseSubjectDTO> courseSubjects;
}
