package com.example.duan.Mapper;

import com.example.duan.DTO.request.UserCreationRequest;
import com.example.duan.DTO.request.UserUpdateRequest;
import com.example.duan.DTO.response.UserResponse;
import com.example.duan.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles",ignore = true)
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    @Mapping(target = "roles",ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
