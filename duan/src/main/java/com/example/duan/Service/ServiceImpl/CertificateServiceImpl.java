package com.example.duan.Service.ServiceImpl;

import com.example.duan.Entity.Certificate;
import com.example.duan.Repository.CertificateRepository;
import com.example.duan.Service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertificateServiceImpl implements CertificateService {
    private final CertificateRepository certificateRepository;

    @Autowired
    public CertificateServiceImpl(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    @Override
    public List<Certificate> getAll(Pageable pageable) {
        return certificateRepository.findAll();
    }

    @Override
    public Optional<Certificate> getById(int id) {
        return certificateRepository.findById(id);
    }

    @Override
    public Certificate createOrUpdate(Certificate certificate) {
        return certificateRepository.save(certificate);
    }

    @Override
    public void deleteById(int id) {
        certificateRepository.deleteById(id);
    }
}
