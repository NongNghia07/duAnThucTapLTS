package com.example.duan.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseSubjectDTO {
    private int id;
    private CourseDTO course;
    private SubjectDTO subject;
}
