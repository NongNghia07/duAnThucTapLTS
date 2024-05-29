package com.example.duan.Service.ServiceImpl;

import com.example.duan.Config.ModelMapperConfig;
import com.example.duan.DTO.CourseDTO;
import com.example.duan.DTO.CourseSubjectDTO;
import com.example.duan.DTO.SubjectDTO;
import com.example.duan.Entity.Course;
import com.example.duan.Entity.CourseSubject;
import com.example.duan.Entity.Subject;
import com.example.duan.Exception.ApiRequestException;
import com.example.duan.Repository.CourseSubjectRepository;
import com.example.duan.Service.CourseSubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseSubjectServiceImpl implements CourseSubjectService {

    private final CourseSubjectRepository courseSubjectRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CourseSubjectServiceImpl(CourseSubjectRepository courseSubjectRepository, ModelMapper modelMapper) {
        this.courseSubjectRepository = courseSubjectRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<CourseSubjectDTO> getAll() {
        List<CourseSubject> courseSubjects = courseSubjectRepository.findAll();
        return (Set<CourseSubjectDTO>) ModelMapperConfig.mapCollection(courseSubjects, CourseSubjectDTO.class, Collectors.toSet());
    }

    @Override
    public CourseSubjectDTO getById(int id) {
        CourseSubject courseSubject = courseSubjectRepository.findById(id).orElseThrow(() -> new ApiRequestException("Course Subject Not Found"));
        return modelMapper.map(courseSubject, CourseSubjectDTO.class);
    }

    @Override
    public Set<CourseSubjectDTO> saveAll(Set<CourseSubjectDTO> courseSubjectDTOs) {
        courseSubjectDTOs.forEach(p -> {
            CourseSubject courseSubject = courseSubjectRepository.findByCourse_IdAndSubject_Id(p.getCourse().getId(), p.getSubject().getId());
            if(p.getId() == 0){
                if(courseSubject != null) {
                    throw new ApiRequestException("Course Subject Already Exists");
                }
            }else {
                if(courseSubject != null) {
                    if(courseSubject.getId() != p.getId()) {
                        throw new ApiRequestException("Course Subject Already Exists");
                    }
                }
            }
        });
        Set<CourseSubject> courseSubjects = (Set<CourseSubject>) ModelMapperConfig.mapCollection(courseSubjectDTOs, CourseSubject.class, Collectors.toSet());
        List<CourseSubject> lst = courseSubjectRepository.saveAll(courseSubjects);

        List<CourseSubject> old = courseSubjectRepository.findByCourse_Id(lst.get(0).getCourse().getId());

        // lấy danh sách courseSubject cần xóa
        // lấy tất cả courseSubject của course_id
        // lấy ra những courseSubject không có trong danh sách courseSubject vừa được saveAll
        List<CourseSubject> lstDelete = old.stream()
                .filter(obj -> lst.stream().noneMatch(item -> item.getId() == obj.getId()))
                .collect(Collectors.toList());

        if(!lstDelete.isEmpty()) courseSubjectRepository.deleteAll(lstDelete);

        return courseSubjectDTOs;
    }

    @Override
    public void deleteAll(Integer course_id) {
        List<CourseSubject> courseSubjects = courseSubjectRepository.findByCourse_Id(course_id);
        courseSubjectRepository.deleteAll(courseSubjects);
    }

    @Override
    public Set<CourseSubjectDTO> findAllByCourseId(Integer course_id) {
        return (Set<CourseSubjectDTO>) ModelMapperConfig.mapCollection(courseSubjectRepository.findByCourse_Id(course_id), CourseSubjectDTO.class, Collectors.toSet());
    }
}
