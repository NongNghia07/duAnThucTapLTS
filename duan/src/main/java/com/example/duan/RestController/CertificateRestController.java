package com.example.duan.RestController;

import com.example.duan.DTO.CertificateDTO;
import com.example.duan.Entity.Certificate;
import com.example.duan.Service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping
    public ResponseEntity<Page<CertificateDTO>> getAllCertificates(Pageable pageable) {
        Page<CertificateDTO> certificatePage = certificateService.findAllCertificates(pageable);
        return new ResponseEntity<>(certificatePage, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CertificateDTO> createCertificate(@RequestBody CertificateDTO certificateDTO) {
        CertificateDTO createdCertificate = certificateService.create(certificateDTO);
        return ResponseEntity.ok().body(createdCertificate);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificateDTO> getCertificateById(@PathVariable("id") int id) {
        CertificateDTO certificateDTO = certificateService.findById(id);
        return ResponseEntity.ok().body(certificateDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCertificates() {
        List<CertificateDTO> certificates = certificateService.findAll();
        return ResponseEntity.ok().body(certificates);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CertificateDTO> updateCertificate(@PathVariable("id") int id, @RequestBody CertificateDTO certificateDTO) {
        CertificateDTO updatedCertificate = certificateService.update(id, certificateDTO);
        return ResponseEntity.ok().body(updatedCertificate);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCertificate(@PathVariable("id") int id) {
        certificateService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
