package com.example.duan.Service.ServiceImpl;

import com.example.duan.Config.ModelMapperConfig;
import com.example.duan.DTO.CourseDTO;
import com.example.duan.DTO.CourseSubjectDTO;
import com.example.duan.DTO.SubjectDTO;
import com.example.duan.Entity.Course;
import com.example.duan.Exception.ApiRequestException;
import com.example.duan.Repository.CourseRepository;
import com.example.duan.Service.CourseService;
import com.example.duan.Service.CourseSubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;
    private final CourseSubjectService courseSubjectService;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, ModelMapper modelMapper, CourseSubjectService courseSubjectService) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
        this.courseSubjectService = courseSubjectService;
    }

    @Override
    public Set<CourseDTO> getAll() {
        List<Course> courses = courseRepository.findAll();
        return (Set<CourseDTO>) ModelMapperConfig.mapCollection(courses, CourseDTO.class, Collectors.toSet());
    }

    @Override
    public Page<CourseDTO> getAll(Pageable pageable) {
        Page<Course> page = courseRepository.findAll(pageable);
        List<CourseDTO> courseDTOS = (List<CourseDTO>) ModelMapperConfig.mapCollection(page.getContent(), CourseDTO.class, Collectors.toList());
        return new PageImpl<>(courseDTOS, pageable, page.getTotalPages());
    }

    @Override
    public CourseDTO create(CourseDTO courseDTO) {
        try {
            Course course = modelMapper.map(courseDTO, Course.class);
            if(courseRepository.findByCode(courseDTO.getCode()).isPresent()){
                throw new ApiRequestException("Course already exists");
            }
            // kiểm tra user tạo khóa học phải có quyền giảng viên thông qua chứng chỉ giảng viên ở bảng certificate
            course.setCode(String.valueOf(randomCode()));
            course.setNumberOfStudent(0);
            course.setNumberOfPurchases(0);
            courseRepository.save(course);
            saveCourseSubjects(course.getId(), courseDTO);
            return modelMapper.map(course, CourseDTO.class);
        }catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public CourseDTO update(CourseDTO courseDTO) {
        try {
            Course course = courseRepository.findById(courseDTO.getId()).orElseThrow(() -> new ApiRequestException("Course not found"));
            courseRepository.save(course);
            saveCourseSubjects(course.getId(), courseDTO);
            return modelMapper.map(course, CourseDTO.class);
        }catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new ApiRequestException("Not found with id: " + id));
        courseRepository.delete(course);
    }

    @Override
    public CourseDTO getById(int id) {
        return modelMapper.map(courseRepository.findById(id).orElseThrow(() -> new ApiRequestException("Not found with id: " + id)), CourseDTO.class);
    }

    private int randomCode() {
        Random random = new Random();
        return random.nextInt(10000000);
    }

    private void saveCourseSubjects(Integer courseId, CourseDTO courseDTO) {
        courseDTO.getCourseSubjects().forEach(p -> {
            CourseDTO cours = new CourseDTO();
            cours.setId(courseId);
            p.setCourse(cours);
        });
        courseSubjectService.saveAll(courseDTO.getCourseSubjects());
    }
}
