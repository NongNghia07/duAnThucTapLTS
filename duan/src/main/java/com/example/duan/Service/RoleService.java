package com.example.duan.Service;

import com.example.duan.DTO.request.RoleRequest;
import com.example.duan.DTO.response.RoleResponse;
import com.example.duan.Mapper.RoleMapper;
import com.example.duan.Repository.PermissionRepository;
import com.example.duan.Repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    public RoleResponse create(RoleRequest request){
        var role = roleMapper.toRole(request);
        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAll(){
        var role = roleRepository.findAll();
        return role.stream().map(roleMapper::toRoleResponse).toList();

    }

    public void delete(String name){
        roleRepository.deleteById(name);
    }

    // List<RoleDTO> getAllRoles();
    // RoleDTO getRoleById(int id);
    // RoleDTO createRole(RoleDTO roleDTO);
    // void deleteRole(int id);
    // RoleDTO updateRole(RoleDTO roleDTO);
}
