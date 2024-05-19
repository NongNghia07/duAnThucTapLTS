package com.example.duan.RestController;

import com.example.duan.Entity.Certificate;
import com.example.duan.Service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class CertificateRestController {
    private final CertificateService certificateService;

    @Autowired
    public CertificateRestController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping("getPage")
    public ResponseEntity<List<Certificate>> getAllCertificates(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(certificateService.getAll(pageable));
    }

    @GetMapping("find/{id}")
    public ResponseEntity<Certificate> getCertificateById(@PathVariable int id) {
        Optional<Certificate> certificateOptional = certificateService.getById(id);
        return certificateOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("create")
    public ResponseEntity<Certificate> createCertificate(@RequestBody Certificate certificate) {
        Certificate createdCertificate = certificateService.createOrUpdate(certificate);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCertificate);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Certificate> updateCertificate(@PathVariable int id, @RequestBody Certificate certificate) {
        Optional<Certificate> existingCertificateOptional = certificateService.getById(id);
        if (existingCertificateOptional.isPresent()) {
            Certificate updatedCertificate = certificateService.createOrUpdate(certificate);
            return ResponseEntity.ok(updatedCertificate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteCertificate(@PathVariable int id) {
        Optional<Certificate> certificateOptional = certificateService.getById(id);
        if (certificateOptional.isPresent()) {
            certificateService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
