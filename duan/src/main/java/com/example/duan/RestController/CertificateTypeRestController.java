package com.example.duan.RestController;

import com.example.duan.DTO.CertificateTypeDTO;
import com.example.duan.Service.CertificateTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certificate-types")
public class CertificateTypeRestController {
    private final CertificateTypeService certificateTypeService;

    @Autowired
    public CertificateTypeRestController(CertificateTypeService certificateTypeService) {
        this.certificateTypeService = certificateTypeService;
    }

    @GetMapping
    public ResponseEntity<Page<CertificateTypeDTO>> getAllCertificateTypes(Pageable pageable) {
        Page<CertificateTypeDTO> certificateTypePage = certificateTypeService.findAllCertificateTypes(pageable);
        return new ResponseEntity<>(certificateTypePage, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CertificateTypeDTO> createCertificateType(@RequestBody CertificateTypeDTO certificateTypeDTO) {
        CertificateTypeDTO createdCertificateType = certificateTypeService.create(certificateTypeDTO);
        return ResponseEntity.ok().body(createdCertificateType);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificateTypeDTO> getCertificateTypeById(@PathVariable("id") int id) {
        CertificateTypeDTO certificateTypeDTO = certificateTypeService.findById(id);
        return ResponseEntity.ok().body(certificateTypeDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CertificateTypeDTO>> getAllCertificateTypes() {
        List<CertificateTypeDTO> certificateTypes = certificateTypeService.findAll();
        return ResponseEntity.ok().body(certificateTypes);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CertificateTypeDTO> updateCertificateType(@PathVariable("id") int id, @RequestBody CertificateTypeDTO certificateTypeDTO) {
        CertificateTypeDTO updatedCertificateType = certificateTypeService.update(id, certificateTypeDTO);
        return ResponseEntity.ok().body(updatedCertificateType);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCertificateType(@PathVariable("id") int id) {
        certificateTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
