package com.example.duan.RestController;

import com.example.duan.DTO.CourseDTO;
import com.example.duan.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/course")
@CrossOrigin("*")
public class CourseRestController {
    private final CourseService courseService;

    @Autowired
    public CourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(courseService.getAll());
    }

    @GetMapping("getPage")
    public ResponseEntity<?> getAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                     @RequestParam(name = "size", defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(courseService.getAll(pageable));
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestPart("courseDTO") CourseDTO courseDTO, @RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok(courseService.create(courseDTO, file));
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestPart("courseDTO") CourseDTO courseDTO, @RequestPart(name = "file") MultipartFile file) {
        return ResponseEntity.ok(courseService.update(courseDTO, file));
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        courseService.delete(id);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(courseService.getById(id));
    }
}
