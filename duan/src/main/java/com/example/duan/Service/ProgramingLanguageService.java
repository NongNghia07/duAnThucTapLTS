package com.example.duan.Service;

import com.example.duan.DTO.ProgramingLanguageDTO;

import java.util.List;

public interface ProgramingLanguageService {
    List<ProgramingLanguageDTO> getAllProgramingLanguage();
    ProgramingLanguageDTO getProgramingLanguageById(int id);
    ProgramingLanguageDTO addProgramingLanguage(ProgramingLanguageDTO programingLanguageDTO);
    ProgramingLanguageDTO updateProgramingLanguage(ProgramingLanguageDTO programingLanguageDTO);
    void deleteProgramingLanguage(int id);
}
