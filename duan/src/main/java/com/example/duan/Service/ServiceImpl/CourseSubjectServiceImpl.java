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
        courseSubjectRepository.saveAll(courseSubjects);
        return courseSubjectDTOs;
    }

    @Override
    public void deleteAll(Set<CourseSubjectDTO> courseSubjectDTOs) {
        courseSubjectDTOs.forEach(p -> {
            CourseSubject courseSubject = courseSubjectRepository.findById(p.getId()).orElseThrow(() -> new ApiRequestException("Course Subject Not Found"));
        });
        Set<CourseSubject> courseSubjects = (Set<CourseSubject>) ModelMapperConfig.mapCollection(courseSubjectDTOs, CourseSubject.class, Collectors.toSet());
        courseSubjectRepository.deleteAll(courseSubjects);
    }
}
