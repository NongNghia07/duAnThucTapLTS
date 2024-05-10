package com.example.duan.Service.ServiceImpl;

import com.example.duan.Config.ModelMapperConfig;
import com.example.duan.DTO.DistrictDTO;
import com.example.duan.Entity.District;
import com.example.duan.Repository.DistrictRepository;
import com.example.duan.Service.DistrictService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository, ModelMapper modelMapper) {
        this.districtRepository = districtRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<DistrictDTO> findAll() {
        List<District> districts = districtRepository.findAll();
        return (Set<DistrictDTO>) ModelMapperConfig.mapCollection(districts, DistrictDTO.class, Collectors.toSet());
    }

    @Override
    public Page<DistrictDTO> findAllPageable(Pageable pageable) {
        return null;
    }

    @Override
    public DistrictDTO update(DistrictDTO districtDTO) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public DistrictDTO findById(int id) {
        return null;
    }
}
