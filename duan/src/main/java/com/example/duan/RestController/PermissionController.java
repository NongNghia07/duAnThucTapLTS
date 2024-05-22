package com.example.duan.RestController;

import com.example.duan.DTO.PermissionDTO;
import com.example.duan.Service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;


    @GetMapping
    public ResponseEntity<List<PermissionDTO>> getAllPermissions() {
        return ResponseEntity.ok(permissionService.getAllPermissions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionDTO> getPermissionById(@PathVariable int id) {
        return ResponseEntity.ok(permissionService.getPermissionById(id));
    }

    @PostMapping
    public ResponseEntity<List<PermissionDTO>> createPermission(@RequestBody List<PermissionDTO> permissionDTOs) {
        return ResponseEntity.ok(permissionService.createPermission(permissionDTOs));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePermission(@RequestParam int id) {
        permissionService.deletePermission(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<PermissionDTO> updatePermission(@RequestBody PermissionDTO permissionDTO) {
        return ResponseEntity.ok(permissionService.updatePermission(permissionDTO));
    }
}