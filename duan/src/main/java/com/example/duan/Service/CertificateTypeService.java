package com.example.duan.Service;

import com.example.duan.DTO.CertificateTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CertificateTypeService {
    Page<CertificateTypeDTO> findAllCertificateTypes(Pageable pageable);
    CertificateTypeDTO create(CertificateTypeDTO certificateTypeDTO);
    CertificateTypeDTO findById(int id);
    List<CertificateTypeDTO> findAll();
    CertificateTypeDTO update(int id, CertificateTypeDTO certificateTypeDTO);
    void delete(int id);
}
