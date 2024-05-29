package com.example.duan.Service.ServiceImpl;

import com.example.duan.Config.ModelMapperConfig;
import com.example.duan.DTO.DistrictDTO;
import com.example.duan.DTO.ProvinceDTO;
import com.example.duan.Entity.District;
import com.example.duan.Entity.Province;
import com.example.duan.Exception.ApiRequestException;
import com.example.duan.Repository.ProvinceRepository;
import com.example.duan.Service.DistrictService;
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
    private final DistrictService districtService;

    @Autowired
    public ProvinceServiceImpl(ProvinceRepository provinceRepository, ModelMapper modelMapper, DistrictService districtService) {
        this.provinceRepository = provinceRepository;
        this.modelMapper = modelMapper;
        this.districtService = districtService;
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
       Province province = provinceRepository.findById(provinceDTO.getId()).orElseThrow(() -> new ApiRequestException("Province not found"));
       province.setName(provinceDTO.getName());
       provinceRepository.save(province);
        saveDistricts(province.getId(), provinceDTO);
        return modelMapper.map(province, ProvinceDTO.class);
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

    @Override
    public ProvinceDTO create(ProvinceDTO provinceDTO) {
        Province province = modelMapper.map(provinceDTO, Province.class);
        if (provinceRepository.findById(provinceDTO.getId()).isPresent()) {
            throw new ApiRequestException("Province already exists");
        }
        provinceRepository.save(province);
        saveDistricts(province.getId(), provinceDTO);
        return modelMapper.map(province, ProvinceDTO.class);
    }
    private void saveDistricts(Integer provinceId, ProvinceDTO provinceDTO) {
        provinceDTO.getDistricts().forEach(p -> {
            ProvinceDTO province = new ProvinceDTO();
            province.setId(provinceId);
            p.setProvince(province);
        });
        districtService.saveAll(provinceDTO.getDistricts());
    }
}
