package com.example.duan.Service;

import com.example.duan.DTO.DoHomeWorkDTO;

import java.util.List;

public interface DoHomeWorkService {
    List<DoHomeWorkDTO> getAllHomeWorks();
    DoHomeWorkDTO getHomeWorkById(int id);
    DoHomeWorkDTO createHomeWork(DoHomeWorkDTO doHomeWorkDTO);
    DoHomeWorkDTO updateHomeWork(int id, DoHomeWorkDTO doHomeWorkDTO);
    void deleteHomeWork(int id);

    DoHomeWorkDTO submitHomeWork(int userId, DoHomeWorkDTO doHomeWorkDTO);

    DoHomeWorkDTO gradeHomeWork(int id, Double grade,String feedback);
}
