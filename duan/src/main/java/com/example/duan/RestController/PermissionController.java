package com.example.duan.RestController;

import com.example.duan.Entity.Permission;
import com.example.duan.Service.Permission.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public List<Permission> getAllPermissions() {
        return permissionService.getAllPermissions();
    }

    @GetMapping("/getPermission")
    public Permission getPermissionById(@RequestParam Long id) {
        return permissionService.getPermissionById(id);
    }

    @PostMapping
    public Permission createPermission(@RequestBody Permission permission) {
        return permissionService.createPermission(permission);
    }

    @PutMapping("/{id}")
    public Permission updatePermission(@PathVariable Long id, @RequestBody Permission permissionDetails) {
        return permissionService.updatePermission(id, permissionDetails);
    }

    @DeleteMapping("/deletePermission")
    public void deletePermission(@RequestParam Long id) {
        permissionService.deletePermission(id);
    }
}
