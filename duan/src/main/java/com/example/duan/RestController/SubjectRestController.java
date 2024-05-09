package com.example.duan.RestController;

import com.example.duan.DTO.SubjectDTO;
import com.example.duan.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subject")
@CrossOrigin("*")
public class SubjectRestController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectRestController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("craete")
    public ResponseEntity<?> craete(@RequestBody SubjectDTO subjectDTO) {
        return ResponseEntity.ok(subjectService.save(subjectDTO));
    }
}
