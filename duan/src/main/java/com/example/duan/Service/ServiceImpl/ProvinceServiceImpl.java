package com.example.duan.Service.ServiceImpl;

import com.example.duan.Config.ModelMapperConfig;
import com.example.duan.DTO.ProvinceDTO;
import com.example.duan.Entity.Province;
import com.example.duan.Exception.ApiRequestException;
import com.example.duan.Repository.ProvinceRepository;
import com.example.duan.Service.ProvinceService;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    private final ProvinceRepository provinceRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProvinceServiceImpl(ProvinceRepository provinceRepository, ModelMapper modelMapper) {
        this.provinceRepository = provinceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<ProvinceDTO> findAll() {
        List<Province> provinces = provinceRepository.findAll();
        return (Set<ProvinceDTO>) ModelMapperConfig.mapCollection(provinces, ProvinceDTO.class, Collectors.toSet());
    }

    @Override
    public Page<ProvinceDTO> findAllPageable(Pageable pageable) {
        Page<Province> page = provinceRepository.findAll(pageable);
        List<ProvinceDTO> provinceDTOS = (List<ProvinceDTO>) ModelMapperConfig.mapCollection(page.getContent(), ProvinceDTO.class, Collectors.toList());
        return new PageImpl<>(provinceDTOS, pageable, page.getTotalPages());
    }

    @Override
    public ProvinceDTO update(ProvinceDTO provinceDTO) {
        try {
            Province province = provinceRepository.findById(provinceDTO.getId()).orElseThrow(() -> new ApiRequestException("Province not found"));
            provinceRepository.save(province);
            return  modelMapper.map(province, ProvinceDTO.class);
        }
        catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        Province province = provinceRepository.findById(id).orElseThrow(() -> new ApiRequestException("Not found with id: " + id));
        provinceRepository.delete(province);
    }

    @Override
    public ProvinceDTO findById(int id) {
        return modelMapper.map(provinceRepository.findById(id).orElseThrow(() -> new ApiRequestException("Not found with id: " + id)), ProvinceDTO.class);
    }


}
