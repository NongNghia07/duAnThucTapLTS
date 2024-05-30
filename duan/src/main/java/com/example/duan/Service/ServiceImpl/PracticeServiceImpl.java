package com.example.duan.Service.ServiceImpl;

import com.example.duan.DTO.PracticeDTO;
import com.example.duan.Entity.Practice;
import com.example.duan.Repository.PracticeRepository;
import com.example.duan.Service.PracticeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PracticeServiceImpl implements PracticeService {
    @Autowired
    private PracticeRepository practiceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PracticeDTO> getAllPractices() {
        return practiceRepository.findAll().stream()
                .map(practice -> modelMapper.map(practice, PracticeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PracticeDTO getPracticeById(int id) {
        Practice practice = practiceRepository.findById(id).orElse(null);
        return practice != null ? modelMapper.map(practice, PracticeDTO.class) : null;
    }

    @Override
    public PracticeDTO createPractice(PracticeDTO practiceDTO) {
        Practice practice = modelMapper.map(practiceDTO, Practice.class);
        practice = practiceRepository.save(practice);
        return modelMapper.map(practice, PracticeDTO.class);
    }

    @Override
    public PracticeDTO updatePractice(PracticeDTO practiceDTO) {
        Practice practice = modelMapper.map(practiceDTO, Practice.class);
        practice = practiceRepository.save(practice);
        return modelMapper.map(practice, PracticeDTO.class);
    }

    @Override
    public void deletePractice(int id) {
        practiceRepository.deleteById(id);
    }
}
