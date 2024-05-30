package com.example.duan.Service.ServiceImpl;

import com.example.duan.DTO.ProgramingLanguageDTO;
import com.example.duan.Entity.ProgramingLanguage;
import com.example.duan.Repository.ProgramingLanguageRepository;
import com.example.duan.Service.ProgramingLanguageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgramingLanguageServiceImpl implements ProgramingLanguageService {

    @Autowired
    private ProgramingLanguageRepository programingLanguageRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<ProgramingLanguageDTO> getAllProgramingLanguage() {
        return programingLanguageRepository.findAll().stream()
                .map(programingLanguage -> modelMapper.map(programingLanguage, ProgramingLanguageDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProgramingLanguageDTO getProgramingLanguageById(int id) {
        ProgramingLanguage programingLanguage = programingLanguageRepository.findById(id).orElse(null);
        return programingLanguage != null ? modelMapper.map(programingLanguage, ProgramingLanguageDTO.class) : null;
    }

    @Override
    public ProgramingLanguageDTO addProgramingLanguage(ProgramingLanguageDTO programingLanguageDTO) {
        ProgramingLanguage programingLanguage = modelMapper.map(programingLanguageDTO, ProgramingLanguage.class);
        programingLanguage = programingLanguageRepository.save(programingLanguage);
        return modelMapper.map(programingLanguage, ProgramingLanguageDTO.class);
    }

    @Override
    public ProgramingLanguageDTO updateProgramingLanguage(ProgramingLanguageDTO programingLanguageDTO) {
        ProgramingLanguage programingLanguage = modelMapper.map(programingLanguageDTO, ProgramingLanguage.class);
        programingLanguage = programingLanguageRepository.save(programingLanguage);
        return modelMapper.map(programingLanguage, ProgramingLanguageDTO.class);
    }

    @Override
    public void deleteProgramingLanguage(int id) {
        programingLanguageRepository.deleteById(id);
    }
}
