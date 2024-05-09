package com.example.duan.RestController;

import com.example.duan.Entity.Role;
import com.example.duan.Service.Permission.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    @PutMapping("/{id}")
    public Role updateRole(@PathVariable Long id, @RequestBody Role roleDetails) {
        return roleService.updateRole(id, roleDetails);
    }

    @DeleteMapping("/deleteRole")
    public void deleteRole(@RequestParam Long id) {
        roleService.deleteRole(id);
    }

    @PostMapping("/{roleId}/permissions/{permissionId}")
    public Role assignPermissionToRole(@PathVariable Long roleId, @PathVariable Long permissionId) {
        return roleService.assignPermissionToRole(roleId, permissionId);
    }

    @DeleteMapping("/{roleId}/permissions/{permissionId}")
    public Role revokePermissionFromRole(@PathVariable Long roleId, @PathVariable Long permissionId) {
        return roleService.revokePermissionFromRole(roleId, permissionId);
    }
}
