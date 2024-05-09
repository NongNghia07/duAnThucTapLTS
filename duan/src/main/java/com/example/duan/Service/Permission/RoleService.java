package com.example.duan.Service.Permission;

import com.example.duan.Entity.Permission;
import com.example.duan.Entity.Role;
import com.example.duan.Repository.PermissionRepository;
import com.example.duan.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Long id, Role roleDetails) {
        Role role = roleRepository.findById(id).orElse(null);
        if (role != null) {
            role.setRoleCode(roleDetails.getRoleCode());
            role.setRoleName(roleDetails.getRoleName());
            return roleRepository.save(role);
        }
        return null;
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    //Cap quyen
    public Role assignPermissionToRole(Long roleId, Long permissionId) {
        Role role = roleRepository.findById(roleId).orElse(null);
        Permission permission = permissionRepository.findById(permissionId).orElse(null);
        if (role != null && permission != null) {
            role.getPermissions().add(permission);
            return roleRepository.save(role);
        }
        return null;
    }

    //Thu hoi quyen
    public Role revokePermissionFromRole(Long roleId, Long permissionId) {
        Role role = roleRepository.findById(roleId).orElse(null);
        Permission permission = permissionRepository.findById(permissionId).orElse(null);
        if (role != null && permission != null) {
            role.getPermissions().remove(permission);
            return roleRepository.save(role);
        }
        return null;
    }

}
