package com.example.duan.Service.ServiceImpl;

import com.example.duan.DTO.CertificateTypeDTO;
import com.example.duan.Entity.CertificateType;
import com.example.duan.Repository.CertificateTypeRepository;
import com.example.duan.Service.CertificateTypeService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CertificateTypeServiceImpl implements CertificateTypeService {

    private final CertificateTypeRepository certificateTypeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CertificateTypeServiceImpl(CertificateTypeRepository certificateTypeRepository, ModelMapper modelMapper) {
        this.certificateTypeRepository = certificateTypeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<CertificateTypeDTO> findAllCertificateTypes(Pageable pageable) {
        Page<CertificateType> certificateTypesPage = certificateTypeRepository.findAll(pageable);
        return certificateTypesPage.map(certificateType -> modelMapper.map(certificateType, CertificateTypeDTO.class));
    }

    @Override
    public CertificateTypeDTO create(CertificateTypeDTO certificateTypeDTO) {
        CertificateType certificateType = modelMapper.map(certificateTypeDTO, CertificateType.class);
        certificateType = certificateTypeRepository.save(certificateType);
        return modelMapper.map(certificateType, CertificateTypeDTO.class);
    }

    @Override
    public CertificateTypeDTO findById(int id) {
        Optional<CertificateType> optionalCertificateType = certificateTypeRepository.findById(id);
        if (optionalCertificateType.isPresent()) {
            return modelMapper.map(optionalCertificateType.get(), CertificateTypeDTO.class);
        }
        throw new EntityNotFoundException("Certificate Type not found with id: " + id);
    }

    @Override
    public List<CertificateTypeDTO> findAll() {
        List<CertificateType> certificateTypes = certificateTypeRepository.findAll();
        return certificateTypes.stream()
                .map(certificateType -> modelMapper.map(certificateType, CertificateTypeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CertificateTypeDTO update(int id, CertificateTypeDTO certificateTypeDTO) {
        CertificateType certificateType = certificateTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Certificate Type not found with id: " + id));

        certificateType.setName(certificateTypeDTO.getName());

        certificateType = certificateTypeRepository.save(certificateType);
        return modelMapper.map(certificateType, CertificateTypeDTO.class);
    }

    @Override
    public void delete(int id) {
        if (!certificateTypeRepository.existsById(id)) {
            throw new EntityNotFoundException("Certificate Type not found with id: " + id);
        }
        certificateTypeRepository.deleteById(id);
    }
}

