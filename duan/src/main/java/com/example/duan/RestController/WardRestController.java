package com.example.duan.RestController;

import com.example.duan.DTO.WardDTO;
import com.example.duan.Service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ward")
public class WardRestController {
    private final WardService wardService;

    @Autowired
    public WardRestController(WardService wardService) {
        this.wardService = wardService;
    }

    @GetMapping("findAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(wardService.findAll());
    }

    @GetMapping("findAllPageable")
    public ResponseEntity<?> findAllPageable(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                             @RequestParam(name = "size", defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(wardService.findAllPageable(pageable));
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody WardDTO wardDTO) {
        return ResponseEntity.ok(wardService.update(wardDTO));
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        wardService.delete(id);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(wardService.findById(id));
    }
}
