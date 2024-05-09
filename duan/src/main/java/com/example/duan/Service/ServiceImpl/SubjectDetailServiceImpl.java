package com.example.duan.Service.ServiceImpl;

import com.example.duan.Config.ModelMapperConfig;
import com.example.duan.DTO.SubjectDetailDTO;
import com.example.duan.Entity.SubjectDetail;
import com.example.duan.Exception.ApiRequestException;
import com.example.duan.Repository.SubjectDetailRepository;
import com.example.duan.Service.SubjectDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SubjectDetailServiceImpl implements SubjectDetailService {

    private final SubjectDetailRepository subjectDetailRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SubjectDetailServiceImpl(SubjectDetailRepository subjectDetailRepository, ModelMapper modelMapper) {
        this.subjectDetailRepository = subjectDetailRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<SubjectDetailDTO> getAll() {
        List<SubjectDetail> subjectDetails = subjectDetailRepository.findAll();
        return (Set<SubjectDetailDTO>) ModelMapperConfig.mapCollection(subjectDetails, SubjectDetailDTO.class, Collectors.toSet());
    }

    @Override
    public SubjectDetailDTO getById(int id) {
        SubjectDetail subjectDetail = subjectDetailRepository.findById(id).orElseThrow(() -> new ApiRequestException("Subject detail not found"));
        return modelMapper.map(subjectDetail, SubjectDetailDTO.class);
    }

    @Override
    public Set<SubjectDetailDTO> createAll(Set<SubjectDetailDTO> subjectDetailDTOs) {
        Set<SubjectDetail> lstSubjectDetail = (Set<SubjectDetail>) ModelMapperConfig.mapCollection(subjectDetailDTOs, SubjectDetail.class, Collectors.toSet());
        subjectDetailRepository.saveAll(lstSubjectDetail);
        return subjectDetailDTOs;
    }

    @Override
    public SubjectDetailDTO update(SubjectDetailDTO subjectDetailDTO) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
