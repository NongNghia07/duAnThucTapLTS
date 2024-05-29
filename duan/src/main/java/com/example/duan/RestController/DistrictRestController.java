package com.example.duan.RestController;

import com.example.duan.DTO.DistrictDTO;
import com.example.duan.Service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/district")
public class DistrictRestController {
    private final DistrictService districtService;

    @Autowired
    public DistrictRestController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping("findAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(districtService.findAll());
    }

    @GetMapping("findAllPageable")
    public ResponseEntity<?> findAllPageable(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                             @RequestParam(name = "size", defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(districtService.findAllPageable(pageable));
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody DistrictDTO districtDTO) {
        return ResponseEntity.ok(districtService.update(districtDTO));
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        districtService.delete(id);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(districtService.findById(id));
    }
}
