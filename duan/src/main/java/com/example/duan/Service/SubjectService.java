package com.example.duan.Service;

import com.example.duan.DTO.SubjectDTO;

import java.util.Set;

public interface SubjectService {
    Set<SubjectDTO> getAll();
    SubjectDTO getById(int id);
    SubjectDTO save(SubjectDTO subjectDTO);
    void deleteById(int id);
}
