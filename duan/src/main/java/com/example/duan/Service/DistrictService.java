package com.example.duan.Service;

import com.example.duan.DTO.DistrictDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface DistrictService {
    Set<DistrictDTO> findAll();
    Page<DistrictDTO> findAllPageable(Pageable pageable);
    DistrictDTO update(DistrictDTO districtDTO);
    Set<DistrictDTO> saveAll(Set<DistrictDTO> districtDTOs);
    void delete(int id);
    DistrictDTO findById(int id);
}
