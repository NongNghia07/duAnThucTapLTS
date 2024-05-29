package com.example.duan.Repository;

import com.example.duan.Entity.Certificate;
import com.example.duan.Entity.CertificateType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CertificateTypeRepository extends JpaRepository<CertificateType, Integer> {
    Optional<CertificateType> findByName(String name);
}

