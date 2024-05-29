package com.example.duan.Service.ServiceImpl;

import com.example.duan.Config.ModelMapperConfig;
import com.example.duan.DTO.CourseDTO;
import com.example.duan.DTO.SubjectDTO;
import com.example.duan.Entity.Course;
import com.example.duan.Entity.Subject;
import com.example.duan.Exception.ApiRequestException;
import com.example.duan.Repository.SubjectRepository;
import com.example.duan.Service.SubjectDetailService;
import com.example.duan.Service.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;
    private final SubjectDetailService subjectDetailService;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository, ModelMapper modelMapper, SubjectDetailService subjectDetailService) {
        this.subjectRepository = subjectRepository;
        this.modelMapper = modelMapper;
        this.subjectDetailService = subjectDetailService;
    }

    @Override
    public Set<SubjectDTO> getAll() {
        List<Subject> subjects = subjectRepository.findAll();
        return (Set<SubjectDTO>) ModelMapperConfig.mapCollection(subjects, SubjectDTO.class, Collectors.toSet());
    }

    @Override
    public Page<SubjectDTO> getAll(Pageable pageable) {
        Page<Subject> page = subjectRepository.findAll(pageable);
        Page<SubjectDTO> pageDTO = page.map(new Function<Subject, SubjectDTO>() {
            @Override
            public SubjectDTO apply(Subject c) {
                SubjectDTO subjectDTO = new SubjectDTO();
                subjectDTO = modelMapper.map(c, SubjectDTO.class);
                return subjectDTO;
            }
        });
        return pageDTO;
    }

    @Override
    public SubjectDTO getById(int id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new ApiRequestException("Subject not found"));
        return modelMapper.map(subject, SubjectDTO.class);
    }

    @Override
    public SubjectDTO save(SubjectDTO subjectDTO) {
        Subject subject = modelMapper.map(subjectDTO, Subject.class);
        subjectRepository.save(subject);
        setSubjectDetail((int) subject.getId(), subjectDTO);
        subjectDetailService.createAll(subjectDTO.getSubjectDetails());
        return modelMapper.map(subject, SubjectDTO.class);
    }

    @Override
    public void deleteById(int id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new ApiRequestException("Subject not found"));
        subjectRepository.delete(subject);
    }

    private SubjectDTO setSubjectDetail(int subject_id, SubjectDTO subjectDTO) {
        subjectDTO.getSubjectDetails().forEach(p -> {
            SubjectDTO s = new SubjectDTO();
            s.setId(subject_id);
            p.setSubject(s);
        });
        return subjectDTO;
    }
}
