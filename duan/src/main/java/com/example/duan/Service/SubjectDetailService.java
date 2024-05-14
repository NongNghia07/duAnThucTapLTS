package com.example.duan.Service;

import com.example.duan.DTO.SubjectDetailDTO;

import java.util.Set;

public interface SubjectDetailService {
    Set<SubjectDetailDTO> getAll();
    SubjectDetailDTO getById(int id);
    Set<SubjectDetailDTO> createAll(Set<SubjectDetailDTO> subjectDetailDTO);
    SubjectDetailDTO update(SubjectDetailDTO subjectDetailDTO);
    void deleteById(int id);
}
