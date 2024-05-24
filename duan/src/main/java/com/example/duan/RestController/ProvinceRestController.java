package com.example.duan.RestController;

import com.example.duan.DTO.DistrictDTO;
import com.example.duan.DTO.ProvinceDTO;
import com.example.duan.Service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/province")
public class ProvinceRestController {
    private final ProvinceService provinceService;

    @Autowired
    public ProvinceRestController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @GetMapping("findAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(provinceService.findAll());
    }

    @GetMapping("findAllPageable")
    public ResponseEntity<?> findAllPageable(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                     @RequestParam(name = "size", defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(provinceService.findAllPageable(pageable));
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody ProvinceDTO provinceDTO) {
        return ResponseEntity.ok(provinceService.create(provinceDTO));
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody ProvinceDTO provinceDTO) {
        return ResponseEntity.ok(provinceService.update(provinceDTO));
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        provinceService.delete(id);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(provinceService.findById(id));
    }
}
