package com.example.duan.Service;

import com.example.duan.DTO.PracticeDTO;

import java.util.List;

public interface PracticeService {
    List<PracticeDTO> getAllPractices();
    PracticeDTO getPracticeById(int id);
    PracticeDTO createPractice(PracticeDTO practiceDTO);
    PracticeDTO updatePractice(PracticeDTO practiceDTO);
    void deletePractice(int id);
}
