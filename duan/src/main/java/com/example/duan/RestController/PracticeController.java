package com.example.duan.RestController;

import com.example.duan.DTO.PracticeDTO;
import com.example.duan.Service.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/practice")
public class PracticeController {
    @Autowired
    private PracticeService practiceService;

    @GetMapping
    public ResponseEntity<List<PracticeDTO>> getAllPractices() {
        return ResponseEntity.ok(practiceService.getAllPractices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PracticeDTO> getPracticeById(@PathVariable int id) {
        return ResponseEntity.ok(practiceService.getPracticeById(id));
    }

    @PostMapping
    public ResponseEntity<PracticeDTO> addPractice(@RequestBody PracticeDTO practiceDTO) {
        return ResponseEntity.ok(practiceService.createPractice(practiceDTO));
    }

    @PutMapping
    public ResponseEntity<PracticeDTO> updatePractice(@RequestBody PracticeDTO practiceDTO) {
        return ResponseEntity.ok(practiceService.updatePractice(practiceDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePractice(@PathVariable int id) {
        practiceService.deletePractice(id);
        return ResponseEntity.ok().build();
    }
}
