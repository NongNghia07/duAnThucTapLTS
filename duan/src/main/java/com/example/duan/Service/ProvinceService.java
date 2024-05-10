package com.example.duan.Service;
import com.example.duan.DTO.ProvinceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface ProvinceService {
    Set<ProvinceDTO> findAll();
    Page<ProvinceDTO> findAllPageable(Pageable pageable);
    ProvinceDTO update(ProvinceDTO provinceDTO);
    void delete(int id);
    ProvinceDTO findById(int id);
}
