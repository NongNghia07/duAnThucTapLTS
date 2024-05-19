package com.example.duan.Repository;

import com.example.duan.Entity.Permission;
import com.example.duan.Entity.Role;
import com.example.duan.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Integer> {
    Permission findByUserAndRole(User user, Role role);
}
