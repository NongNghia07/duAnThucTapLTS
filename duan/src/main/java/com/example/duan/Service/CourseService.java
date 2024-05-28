package com.example.duan.Service;

import com.example.duan.DTO.CourseDTO;
import com.example.duan.Entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface CourseService {
    Set<CourseDTO> getAll();
    Page<CourseDTO> getAll(String keyword, Pageable pageable);
    CourseDTO create(CourseDTO courseDTO, MultipartFile file);
    CourseDTO update(CourseDTO courseDTO, MultipartFile file);
    void delete(List<Integer> lst);
    CourseDTO getById(int id);
}
