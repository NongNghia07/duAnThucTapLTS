package com.example.duan.Service.ServiceImpl;

import com.example.duan.DTO.RoleDTO;
import com.example.duan.Entity.Role;
import com.example.duan.Repository.RoleRepository;
import com.example.duan.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@Service
//public class RoleServiceImpl implements RoleService {
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Override
//    public List<RoleDTO> getAllRoles() {
//        return roleRepository.findAll().stream()
//                .map(role -> new RoleDTO(role.getId(), role.getRoleName()))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public RoleDTO getRoleById(int id) {
//        Role role = roleRepository.findById(id).orElse(null);
//        if (role != null) {
//            return new RoleDTO(role.getId(), role.getRoleName());
//        }
//        return null;
//    }
//
//    @Override
//    public RoleDTO createRole(RoleDTO roleDTO) {
//        Role role = new Role();
//        role.setRoleName(roleDTO.getRoleName());
//        role = roleRepository.save(role);
//        return new RoleDTO(role.getId(), role.getRoleName());
//    }
//
//    @Override
//    public void deleteRole(int id) {
//        roleRepository.deleteById(id);
//    }
//
//    @Override
//    public RoleDTO updateRole(RoleDTO roleDTO) {
//        Role role = roleRepository.findById(roleDTO.getId()).orElse(null);
//        if (role != null) {
//            role.setRoleName(roleDTO.getRoleName());
//            role = roleRepository.save(role);
//            return new RoleDTO(role.getId(), role.getRoleName());
//        }
//        return null;
//    }
//}
