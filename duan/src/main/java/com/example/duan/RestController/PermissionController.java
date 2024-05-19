package com.example.duan.RestController;

import com.example.duan.DTO.PermissionDTO;
import com.example.duan.Entity.Permission;
import com.example.duan.Entity.User;
import com.example.duan.Repository.UserRepository;
import com.example.duan.Service.Permission.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<PermissionDTO> getAllPermissions() {
        return permissionService.getAllPermissions();
    }

    @GetMapping("/getPermission")
    public Permission getPermissionById(@RequestParam int id) {
        return permissionService.getPermissionById(id);
    }

    @PostMapping
    public Permission createPermission(@RequestBody Permission permission) {
        return permissionService.createPermission(permission);
    }

    @PutMapping("/{id}")
    public Permission updatePermission(@PathVariable int id, @RequestBody Permission permissionDetails) {
        return permissionService.updatePermission(id, permissionDetails);
    }

    @DeleteMapping("/deletePermission")
    public void deletePermission(@RequestParam int id) {
        permissionService.deletePermission(id);
    }



    @PostMapping("/create")
    public List<PermissionDTO> createAll (@RequestBody List<PermissionDTO> permissionDTOS){
        return permissionService.createAll(permissionDTOS);
    }
}
