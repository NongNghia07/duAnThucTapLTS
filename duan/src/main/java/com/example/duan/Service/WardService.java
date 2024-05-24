package com.example.duan.Service;

import com.example.duan.DTO.WardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface WardService {
    Set<WardDTO> findAll();
    Page<WardDTO> findAllPageable(Pageable pageable);
    WardDTO update(WardDTO wardDTO);
    Set<WardDTO> saveAll(Set<WardDTO> wardDTOs);
    void delete(int id);
    WardDTO findById(int id);
}
