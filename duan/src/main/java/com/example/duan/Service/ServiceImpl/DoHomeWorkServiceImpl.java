package com.example.duan.Service.ServiceImpl;

import com.example.duan.DTO.DoHomeWorkDTO;
import com.example.duan.Entity.DoHomeWork;
import com.example.duan.Entity.Grade;
import com.example.duan.Entity.User;
import com.example.duan.Enum.HomeWorkStatus;
import com.example.duan.Repository.DoHomeWorkRepository;
import com.example.duan.Repository.UserRepository;
import com.example.duan.Service.DoHomeWorkService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoHomeWorkServiceImpl implements DoHomeWorkService {

    @Autowired
    private DoHomeWorkRepository doHomeWorkRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<DoHomeWorkDTO> getAllHomeWorks() {
        return doHomeWorkRepository.findAll().stream()
                .map(doHomeWork -> modelMapper.map(doHomeWork, DoHomeWorkDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DoHomeWorkDTO getHomeWorkById(int id) {
        DoHomeWork doHomeWork = doHomeWorkRepository.findById(id).orElse(null);
        return doHomeWork != null ? modelMapper.map(doHomeWork, DoHomeWorkDTO.class) : null;
    }

    @Override
    public DoHomeWorkDTO createHomeWork(DoHomeWorkDTO doHomeWorkDTO) {
        DoHomeWork doHomeWork = modelMapper.map(doHomeWorkDTO, DoHomeWork.class);
        doHomeWork = doHomeWorkRepository.save(doHomeWork);
        return modelMapper.map(doHomeWork, DoHomeWorkDTO.class);
    }

    @Override
    public DoHomeWorkDTO updateHomeWork(int id, DoHomeWorkDTO doHomeWorkDTO) {
        DoHomeWork doHomeWork = doHomeWorkRepository.findById(id).orElseThrow(() -> new RuntimeException("Homework not found"));
        doHomeWork = modelMapper.map(doHomeWorkDTO, DoHomeWork.class);
        doHomeWork.setId(id); // Ensure the ID is preserved
        doHomeWork = doHomeWorkRepository.save(doHomeWork);
        return modelMapper.map(doHomeWork, DoHomeWorkDTO.class);
    }

    @Override
    public void deleteHomeWork(int id) {
        doHomeWorkRepository.deleteById(id);
    }

    @Override
    public DoHomeWorkDTO submitHomeWork(int userId, DoHomeWorkDTO doHomeWorkDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        DoHomeWork doHomeWork = modelMapper.map(doHomeWorkDTO, DoHomeWork.class);
        doHomeWork.setUser(user);
        doHomeWork.setDoneTime(LocalDateTime.now());
        doHomeWork.setFinished(true);
        doHomeWork.setHomeWorkStatus(HomeWorkStatus.DALAM);

        DoHomeWork savedDoHomeWork = doHomeWorkRepository.save(doHomeWork);

        return modelMapper.map(savedDoHomeWork, DoHomeWorkDTO.class);
    }

    @Override
    public DoHomeWorkDTO gradeHomeWork(int id, Double grade, String feedback) {
        DoHomeWork doHomeWork = doHomeWorkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Homework not found"));

        Grade gradeObj = new Grade();
        gradeObj.setGrade(grade);
        gradeObj.setFeedback(feedback);

        doHomeWork.setGrade(gradeObj);
        DoHomeWork savedDoHomeWork = doHomeWorkRepository.save(doHomeWork);

        // Map the saved DoHomeWork entity to DoHomeWorkDTO
        DoHomeWorkDTO doHomeWorkDTO = modelMapper.map(savedDoHomeWork, DoHomeWorkDTO.class);

        return doHomeWorkDTO;
    }
}
