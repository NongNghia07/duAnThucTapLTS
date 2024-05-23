package com.example.duan.Service;

import com.example.duan.DTO.CertificateDTO;
import com.example.duan.Entity.Certificate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface CertificateService {
    Page<CertificateDTO> findAllCertificates(Pageable pageable);
    CertificateDTO create(CertificateDTO certificateDTO);
    CertificateDTO findById(int id);
    List<CertificateDTO> findAll();
    CertificateDTO update(int id, CertificateDTO certificateDTO);
    void delete(int id);


}


