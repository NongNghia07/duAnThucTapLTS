package com.example.duan.Service.Permission;

import com.example.duan.Config.ModelMapperConfig;
import com.example.duan.DTO.PermissionDTO;
import com.example.duan.Entity.Permission;
import com.example.duan.Entity.Role;
import com.example.duan.Entity.User;
import com.example.duan.Repository.PermissionRepository;
import com.example.duan.Repository.RoleRepository;
import com.example.duan.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionService implements InterPermission{
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public List<PermissionDTO> getAllPermissions() {
        return (List<PermissionDTO>) ModelMapperConfig.mapCollection(permissionRepository.findAll(), PermissionDTO.class, Collectors.toList());
    }

    public Permission getPermissionById(int id) {
        return permissionRepository.findById(id).orElse(null);
    }

    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    public List<PermissionDTO> createAll(List<PermissionDTO> permissionDTOS) {
        List<Permission> lst = ModelMapperConfig.mapCollection(permissionDTOS, Permission.class);
        permissionRepository.saveAll(lst);
        return ModelMapperConfig.mapCollection(lst, PermissionDTO.class);
    }




    public Permission updatePermission(int id, Permission permissionDetails) {
        Permission permission = permissionRepository.findById(id).orElse(null);
        if (permission != null) {
            permission.setUser(permissionDetails.getUser());
            permission.setRole(permissionDetails.getRole());
            return permissionRepository.save(permission);
        }
        return null;
    }

    public void deletePermission(int id) {
        permissionRepository.deleteById(id);
    }


}
