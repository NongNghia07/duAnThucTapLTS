package com.example.duan.Service;

import com.example.duan.DTO.CourseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface CourseService {
    Set<CourseDTO> getAll();
    Page<CourseDTO> getAll(Pageable pageable);
    CourseDTO create(CourseDTO courseDTO);
    CourseDTO update(CourseDTO courseDTO);
    void delete(int id);
    CourseDTO getById(int id);
}
