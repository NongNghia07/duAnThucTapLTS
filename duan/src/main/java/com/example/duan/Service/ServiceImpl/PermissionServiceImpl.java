//package com.example.duan.Service.ServiceImpl;
//
//import com.example.duan.DTO.PermissionDTO;
//import com.example.duan.Entity.Permission;
//import com.example.duan.Entity.Role;
//import com.example.duan.Entity.User;
//import com.example.duan.Repository.PermissionRepository;
//import com.example.duan.Repository.UserRepository;
//import com.example.duan.Service.PermissionService;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//@Service
//public class PermissionServiceImpl implements PermissionService {
//    @Autowired
//    private PermissionRepository permissionRepository;
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public List<PermissionDTO> getAllPermissions() {
//        return permissionRepository.findAll().stream()
//                .map(permission -> modelMapper.map(permission, PermissionDTO.class))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public PermissionDTO getPermissionById(int id) {
//        Permission permission = permissionRepository.findById(id).orElse(null);
//        if (permission != null) {
//            return modelMapper.map(permission, PermissionDTO.class);
//        }
//        return null;
//    }
//
//    @Override
//    public List<PermissionDTO> createPermission(List<PermissionDTO> permissionDTOs) {
//        List<Permission> permissions = permissionDTOs.stream()
//                .map(permissionDTO -> {
//                    Permission permission = modelMapper.map(permissionDTO, Permission.class);
//                    if (permissionDTO.getUser() != null) {
//                        User user = userRepository.findById(permissionDTO.getUser().getId()).orElse(null);
//                        if (user != null) {
//                            permission.setUser(user);
//                        } else {
//                            throw new RuntimeException("User with id " + permissionDTO.getUser().getId() + " not found");
//                        }
//                    }
//                    return permissionRepository.save(permission);
//                })
//                .collect(Collectors.toList());
//
//        return permissions.stream()
//                .map(permission -> modelMapper.map(permission, PermissionDTO.class))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public void deletePermission(int id) {
//        permissionRepository.deleteById(id);
//    }
//
//    @Override
//    public PermissionDTO updatePermission(PermissionDTO permissionDTO) {
//        Permission permission = permissionRepository.findById(permissionDTO.getId()).orElse(null);
//        if (permission != null) {
//            permission.setUser(modelMapper.map(permissionDTO.getUser(), User.class));
//            permission.setRole(modelMapper.map(permissionDTO.getRole(), Role.class));
//            permission = permissionRepository.save(permission);
//            return modelMapper.map(permission, PermissionDTO.class);
//        }
//        return null;
//    }
//}
