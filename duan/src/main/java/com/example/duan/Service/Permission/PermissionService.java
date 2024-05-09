package com.example.duan.Service.Permission;

import com.example.duan.Entity.Permission;
import com.example.duan.Repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    public Permission getPermissionById(Long id) {
        return permissionRepository.findById(id).orElse(null);
    }

    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    public Permission updatePermission(Long id, Permission permissionDetails) {
        Permission permission = permissionRepository.findById(id).orElse(null);
        if (permission != null) {
            permission.setUser(permissionDetails.getUser());
            permission.setRole(permissionDetails.getRole());
            return permissionRepository.save(permission);
        }
        return null;
    }

    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }
}
