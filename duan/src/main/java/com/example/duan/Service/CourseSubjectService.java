package com.example.duan.Service;

import com.example.duan.DTO.CourseDTO;
import com.example.duan.DTO.CourseSubjectDTO;
import com.example.duan.DTO.SubjectDTO;

import java.util.Set;

public interface CourseSubjectService {
    Set<CourseSubjectDTO> getAll();
    CourseSubjectDTO getById(int id);
    Set<CourseSubjectDTO> saveAll(Set<CourseSubjectDTO> courseSubjectDTOs);
    void deleteAll(Set<CourseSubjectDTO> courseSubjectDTOs);
}
