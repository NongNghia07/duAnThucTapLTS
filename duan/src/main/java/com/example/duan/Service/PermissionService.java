package com.example.duan.Service;

import com.example.duan.DTO.PermissionDTO;

import java.util.List;

public interface PermissionService {
    List<PermissionDTO> getAllPermissions();
    PermissionDTO getPermissionById(int id);

    List<PermissionDTO> createPermission(List<PermissionDTO> permissionDTOs);

    void deletePermission(int id);
    PermissionDTO updatePermission(PermissionDTO permissionDTO);
}
