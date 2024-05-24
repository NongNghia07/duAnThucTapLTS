package com.example.duan.Mapper;

import com.example.duan.DTO.request.RoleRequest;
import com.example.duan.DTO.response.RoleResponse;
import com.example.duan.Entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);

}
