package com.example.duan.RestController;

import com.example.duan.DTO.DoHomeWorkDTO;
import com.example.duan.Service.DoHomeWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/homeworks")
public class DoHomeWorkController {
    @Autowired
    private DoHomeWorkService doHomeWorkService;

    @GetMapping
    public ResponseEntity<List<DoHomeWorkDTO>> getAllHomeWorks() {
        return ResponseEntity.ok(doHomeWorkService.getAllHomeWorks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoHomeWorkDTO> getHomeWorkById(@PathVariable int id) {
        return ResponseEntity.ok(doHomeWorkService.getHomeWorkById(id));
    }

    @PostMapping
    public ResponseEntity<DoHomeWorkDTO> createHomeWork(@RequestBody DoHomeWorkDTO doHomeWorkDTO) {
        return ResponseEntity.ok(doHomeWorkService.createHomeWork(doHomeWorkDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoHomeWorkDTO> updateHomeWork(@PathVariable int id, @RequestBody DoHomeWorkDTO doHomeWorkDTO) {
        return ResponseEntity.ok(doHomeWorkService.updateHomeWork(id, doHomeWorkDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHomeWork(@PathVariable int id) {
        doHomeWorkService.deleteHomeWork(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/submit")
    public ResponseEntity<DoHomeWorkDTO> submitHomeWork(@PathVariable int userId, @RequestBody DoHomeWorkDTO doHomeWorkDTO) {
        return ResponseEntity.ok(doHomeWorkService.submitHomeWork(userId, doHomeWorkDTO));
    }

    @PostMapping("/{id}/grade")
    public ResponseEntity<DoHomeWorkDTO> gradeHomeWork(@PathVariable int id, @RequestParam Double grade, @RequestParam String feedback) {
        return ResponseEntity.ok(doHomeWorkService.gradeHomeWork(id, grade, feedback));
    }
}
