package com.example.duan.Service;

import com.example.duan.DTO.SubjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface SubjectService {
    Set<SubjectDTO> getAll();
    Page<SubjectDTO> getAll(Pageable pageable);
    SubjectDTO getById(int id);
    SubjectDTO save(SubjectDTO subjectDTO);
    void deleteById(int id);
}
