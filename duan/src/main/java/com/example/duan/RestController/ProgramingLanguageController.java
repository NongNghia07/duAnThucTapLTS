package com.example.duan.RestController;

import com.example.duan.DTO.ProgramingLanguageDTO;
import com.example.duan.Service.ProgramingLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/programingLanguage")
public class ProgramingLanguageController {
    @Autowired
    private ProgramingLanguageService programingLanguageService;

    @GetMapping
    public ResponseEntity<List<ProgramingLanguageDTO>> getAllProgramingLanguages() {
        return ResponseEntity.ok(programingLanguageService.getAllProgramingLanguage());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramingLanguageDTO> getProgramingLanguageById(@PathVariable int id) {
        return ResponseEntity.ok(programingLanguageService.getProgramingLanguageById(id));
    }

    @PostMapping
    public ResponseEntity<ProgramingLanguageDTO> addProgramingLanguage(@RequestBody ProgramingLanguageDTO programingLanguageDTO) {
        return ResponseEntity.ok(programingLanguageService.addProgramingLanguage(programingLanguageDTO));
    }

    @PutMapping
    public ResponseEntity<ProgramingLanguageDTO> updateProgramingLanguage(@RequestBody ProgramingLanguageDTO programingLanguageDTO) {
        return ResponseEntity.ok(programingLanguageService.updateProgramingLanguage(programingLanguageDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgramingLanguage(@PathVariable int id) {
        programingLanguageService.deleteProgramingLanguage(id);
        return ResponseEntity.ok().build();
    }
}
