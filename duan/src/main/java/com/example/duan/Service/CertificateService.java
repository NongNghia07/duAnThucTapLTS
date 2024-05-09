package com.example.duan.Service;

import com.example.duan.Entity.Certificate;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CertificateService {
    List<Certificate> getAll(Pageable pageable);
    Optional<Certificate> getById(int id);
    Certificate createOrUpdate(Certificate certificate);
    void deleteById(int id);
}
