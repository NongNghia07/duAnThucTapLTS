package com.example.duan.Mapper;

import com.example.duan.DTO.request.PermissionRequest;
import com.example.duan.DTO.response.PermissionResponse;
import com.example.duan.Entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermisssionResponse(Permission permission);

}