package com.example.duan.Service;

import com.example.duan.DTO.RoleDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> getAllRoles();
    RoleDTO getRoleById(int id);
    RoleDTO createRole(RoleDTO roleDTO);
    void deleteRole(int id);
    RoleDTO updateRole(RoleDTO roleDTO);
}
