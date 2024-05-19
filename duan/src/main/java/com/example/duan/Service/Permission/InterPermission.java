package com.example.duan.Service.Permission;

import com.example.duan.DTO.PermissionDTO;
import com.example.duan.Entity.Permission;

import java.util.List;

public interface InterPermission {
    public List<PermissionDTO> getAllPermissions();
    public Permission getPermissionById(int id);
    public List<PermissionDTO> createAll (List<PermissionDTO> permissionDTOS);
    public Permission updatePermission(int id, Permission permissionDetails);
    public void deletePermission(int id);
}
