package com.example.duan.Service.ServiceImpl;

import com.example.duan.Config.ModelMapperConfig;
import com.example.duan.DTO.DistrictDTO;
import com.example.duan.Entity.District;
import com.example.duan.Exception.ApiRequestException;
import com.example.duan.Repository.DistrictRepository;
import com.example.duan.Service.DistrictService;
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
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;
    private final ModelMapper modelMapper;
    private final WardService wardService;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository, ModelMapper modelMapper, WardService wardService) {
        this.districtRepository = districtRepository;
        this.modelMapper = modelMapper;
        this.wardService = wardService;
    }

    @Override
    public Set<DistrictDTO> findAll() {
        List<District> districts = districtRepository.findAll();
        return (Set<DistrictDTO>) ModelMapperConfig.mapCollection(districts, DistrictDTO.class, Collectors.toSet());
    }

    @Override
    public Page<DistrictDTO> findAllPageable(Pageable pageable) {
        Page<District> page = districtRepository.findAll(pageable);
        List<DistrictDTO> districtDTOS = (List<DistrictDTO>) ModelMapperConfig.mapCollection(page.getContent(), DistrictDTO.class, Collectors.toList());
        return new PageImpl<>(districtDTOS, pageable, page.getTotalPages());
    }

    @Override
    public DistrictDTO update(DistrictDTO districtDTO) {
        District district = districtRepository.findById(districtDTO.getId()).orElseThrow(() -> new ApiRequestException("District not found"));
        district.setName(districtDTO.getName());
        districtRepository.save(district);
        saveWards(district.getId(), districtDTO);
        return modelMapper.map(district, DistrictDTO.class);
    }

    @Override
    public Set<DistrictDTO> saveAll(Set<DistrictDTO> districtDTOs) {
        districtDTOs.forEach(p -> {
            District district = districtRepository.findByProvince_Id(p.getProvince().getId());
            if(p.getId() == 0){
                if(district != null) {
                    throw new ApiRequestException("Dítrict Already Exists");
                }
            }else {
                if(district != null) {
                    if(district.getId() != p.getId()) {
                        throw new ApiRequestException("Dítrict Already Exists");
                    }
                }
            }
        });
        Set<District> districts = (Set<District>) ModelMapperConfig.mapCollection(districtDTOs, District.class, Collectors.toSet());
        districtRepository.saveAll(districts);
        return districtDTOs;
    }

    @Override
    public void delete(int id) {
        District district = districtRepository.findById(id).orElseThrow(() -> new ApiRequestException("Not found with id: " + id));
        districtRepository.delete(district);
    }

    @Override
    public DistrictDTO findById(int id) {
        return modelMapper.map(districtRepository.findById(id).orElseThrow(() -> new ApiRequestException("Not found with id: " + id)), DistrictDTO.class);
    }

    private void saveWards(Integer districtId, DistrictDTO districtDTO) {
        districtDTO.getWards().forEach(p -> {
            DistrictDTO district = new DistrictDTO();
            district.setId(districtId);
            p.setDistrict(district);
        });
        wardService.saveAll(districtDTO.getWards());
    }
}
