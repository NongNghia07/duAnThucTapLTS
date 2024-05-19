package com.example.duan.Service.Permission;

import com.example.duan.Entity.Role;

import java.util.List;

public interface InterRole {
    public List<Role> getAllRoles();
    public Role getRoleById(int id);
    public Role createRole(Role role);
    public Role updateRole(int id, Role roleDetails);
    public void deleteRole(int id);
}
