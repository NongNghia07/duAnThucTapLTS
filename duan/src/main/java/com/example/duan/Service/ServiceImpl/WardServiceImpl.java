package com.example.duan.Service.ServiceImpl;

import com.example.duan.Config.ModelMapperConfig;
import com.example.duan.DTO.WardDTO;
import com.example.duan.Entity.Ward;
import com.example.duan.Exception.ApiRequestException;
import com.example.duan.Repository.WardRepository;
import com.example.duan.Service.WardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WardServiceImpl implements WardService {
    private final WardRepository wardRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public WardServiceImpl(WardRepository wardRepository, ModelMapper modelMapper) {
        this.wardRepository = wardRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<WardDTO> findAll() {
        List<Ward> wards = wardRepository.findAll();
        return (Set<WardDTO>) ModelMapperConfig.mapCollection(wards, WardDTO.class, Collectors.toSet());
    }

    @Override
    public Page<WardDTO> findAllPageable(Pageable pageable) {
        Page<Ward> page = wardRepository.findAll(pageable);
        List<WardDTO> wardDTOS = (List<WardDTO>) ModelMapperConfig.mapCollection(page.getContent(), WardDTO.class, Collectors.toList());
        return new PageImpl<>(wardDTOS, pageable, page.getTotalPages());
    }

    @Override
    public WardDTO update(WardDTO wardDTO) {
        try {
            Ward ward = wardRepository.findById(wardDTO.getId()).orElseThrow(() -> new ApiRequestException("Ward not found"));
            wardRepository.save(ward);
            return modelMapper.map(ward, WardDTO.class);
        }
        catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public Set<WardDTO> saveAll(Set<WardDTO> wardDTOs) {
       wardDTOs.forEach(p -> {
            Ward ward = wardRepository.findByDistrict_Id(p.getDistrict().getId());
            if(p.getId() == 0){
                if(ward != null) {
                    throw new ApiRequestException("Ward Already Exists");
                }
            }else {
                if(ward != null) {
                    if(ward.getId() != p.getId()) {
                        throw new ApiRequestException("Ward Already Exists");
                    }
                }
            }
        });
        Set<Ward> wards = (Set<Ward>) ModelMapperConfig.mapCollection(wardDTOs, Ward.class, Collectors.toSet());
        wardRepository.saveAll(wards);
        return wardDTOs;
    }

    @Override
    public void delete(int id) {
        Ward ward = wardRepository.findById(id).orElseThrow(() -> new ApiRequestException("Not found with id: " + id));
        wardRepository.delete(ward);
    }

    @Override
    public WardDTO findById(int id) {
        return modelMapper.map(wardRepository.findById(id).orElseThrow(() -> new ApiRequestException("Not found with id: " + id)), WardDTO.class);
    }
}
