package com.example.duan.Service.ServiceImpl;

import com.example.duan.Config.ModelMapperConfig;
import com.example.duan.DTO.CourseDTO;
import com.example.duan.DTO.UserDTO;
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
        Course course = modelMapper.map(courseDTO, Course.class);
        if(courseRepository.findByCode(courseDTO.getCode()).isPresent()){
            throw new ApiRequestException("Course already exists");
        }

        // kiểm tra user tạo khóa học phải có quyền giảng viên thông qua chứng chỉ giảng viên ở bảng certificate
        course.setCode(String.valueOf(randomCode()));
        course.setNumberOfStudent(0);
        course.setNumberOfPurchases(0);

        // Lưu khóa học mới vào bảng `courses`
        courseRepository.save(course);

        // Lưu tất cả các môn học vào bảng `course_subjects` của khóa học vừa tạo
        saveCourseSubjects(course.getId(), courseDTO);

        return modelMapper.map(course, CourseDTO.class);
    }

    @Override
    public CourseDTO update(CourseDTO courseDTO) {
        // Tìm kiếm khóa học
        Course course = courseRepository.findById(courseDTO.getId()).orElseThrow(() -> new ApiRequestException("Course not found"));

        // Update các trường của khóa học
        course.setName(courseDTO.getName());
        course.setIntroduce(courseDTO.getIntroduce());
        course.setImageCourse(courseDTO.getImageCourse());
        course.setPrice(course.getPrice());
        course.setTotalCourseDuration(courseDTO.getTotalCourseDuration());

        // Lưu khóa học
        courseRepository.save(course);

        // Lưu Môn học vào khóa học
        saveCourseSubjects(course.getId(), courseDTO);

        return modelMapper.map(course, CourseDTO.class);
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
