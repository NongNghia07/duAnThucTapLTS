package com.example.duan.Service;

import com.example.duan.DTO.request.PermissionRequest;
import com.example.duan.DTO.response.PermissionResponse;
import com.example.duan.Entity.Permission;
import com.example.duan.Mapper.PermissionMapper;
import com.example.duan.Repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request){
        Permission permission = permissionMapper.toPermission(request);
        return permissionMapper.toPermisssionResponse(permissionRepository.save(permission));
    }

    public List<PermissionResponse> getAll(){
        var permission = permissionRepository.findAll();
        return permission.stream().map(permissionMapper::toPermisssionResponse).toList();

    }

    public void delete(String name){
        permissionRepository.deleteById(name);
    }
}

