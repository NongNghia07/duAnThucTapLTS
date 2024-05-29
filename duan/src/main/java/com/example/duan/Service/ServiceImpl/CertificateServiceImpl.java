package com.example.duan.Service.ServiceImpl;


import com.example.duan.Config.ModelMapperConfig;
import com.example.duan.DTO.CertificateDTO;
import com.example.duan.Entity.Certificate;
import com.example.duan.Repository.CertificateRepository;
import com.example.duan.Service.CertificateService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CertificateServiceImpl(CertificateRepository certificateRepository, ModelMapper modelMapper) {
        this.certificateRepository = certificateRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<CertificateDTO> findAllCertificates(Pageable pageable) {
        Page<Certificate> certificatesPage = certificateRepository.findAll(pageable);
        return certificatesPage.map(certificate -> modelMapper.map(certificate, CertificateDTO.class));
    }

    @Override
    public CertificateDTO create(CertificateDTO certificateDTO) {
        Certificate certificate = modelMapper.map(certificateDTO, Certificate.class);
        certificate = certificateRepository.save(certificate);
        return modelMapper.map(certificate, CertificateDTO.class);
    }

    @Override
    public CertificateDTO findById(int id) {
        Optional<Certificate> optionalCertificate = certificateRepository.findById(id);
        if (optionalCertificate.isPresent()) {
            return modelMapper.map(optionalCertificate.get(), CertificateDTO.class);
        }
        throw new EntityNotFoundException("Certificate not found with id: " + id);
    }

    @Override
    public List<CertificateDTO> findAll() {
        List<Certificate> certificates = certificateRepository.findAll();
        return certificates.stream()
                .map(certificate -> modelMapper.map(certificate, CertificateDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CertificateDTO update(int id, CertificateDTO certificateDTO) {
        Certificate certificate = certificateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Certificate not found with id: " + id));

        certificate.setName(certificateDTO.getName());
        certificate.setDescription(certificateDTO.getDescription());
        certificate.setImage(certificateDTO.getImage());
        certificateDTO.setCertificateTypeId(certificateDTO.getCertificateTypeId());

        certificate = certificateRepository.save(certificate);
        return modelMapper.map(certificate, CertificateDTO.class);
    }

    @Override
    public void delete(int id) {
        if (!certificateRepository.existsById(id)) {
            throw new EntityNotFoundException("Certificate not found with id: " + id);
        }
        certificateRepository.deleteById(id);
    }


}




