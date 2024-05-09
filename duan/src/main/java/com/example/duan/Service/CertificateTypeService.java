package com.example.duan.Service;

import com.example.duan.Entity.CertificateType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CertificateTypeService {
    List<CertificateType> getAll();
    Optional<CertificateType> getById(int id);
    CertificateType createOrUpdate(CertificateType certificateType);
    void deleteById(int id);
}
