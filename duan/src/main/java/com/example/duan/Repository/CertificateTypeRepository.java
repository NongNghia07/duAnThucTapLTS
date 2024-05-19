package com.example.duan.Repository;

import com.example.duan.Entity.Certificate;
import com.example.duan.Entity.CertificateType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CertificateTypeRepository extends JpaRepository<CertificateType, Integer> {
    Optional<CertificateType> findById(int id);
}
