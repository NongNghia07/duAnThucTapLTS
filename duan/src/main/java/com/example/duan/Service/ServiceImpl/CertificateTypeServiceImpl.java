package com.example.duan.Service.ServiceImpl;

import com.example.duan.Entity.CertificateType;
import com.example.duan.Repository.CertificateTypeRepository;
import com.example.duan.Service.CertificateTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CertificateTypeServiceImpl implements CertificateTypeService {
    private final CertificateTypeRepository certificateTypeRepository;

    @Autowired
    public CertificateTypeServiceImpl(CertificateTypeRepository certificateTypeRepository) {
        this.certificateTypeRepository = certificateTypeRepository;
    }

    @Override
    public List<CertificateType> getAll() {
        return certificateTypeRepository.findAll();
    }

    @Override
    public Optional<CertificateType> getById(int id) {
        return certificateTypeRepository.findById(id);
    }

    @Override
    public CertificateType createOrUpdate(CertificateType certificateType) {
        return certificateTypeRepository.save(certificateType);
    }

    @Override
    public void deleteById(int id) {
        certificateTypeRepository.deleteById(id);
    }
}
