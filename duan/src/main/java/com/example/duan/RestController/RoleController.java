package com.example.duan.RestController;

import com.example.duan.DTO.request.RoleRequest;
import com.example.duan.DTO.response.ApiResponse;
import com.example.duan.DTO.response.RoleResponse;
import com.example.duan.Service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import com.example.duan.DTO.RoleDTO;
import com.example.duan.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/role")
public class RoleController {

    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request){
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll(){
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/{role}")
    ApiResponse<Void> delete(@PathVariable String role){
        roleService.delete(role);
        return ApiResponse.<Void>builder().build();
    }

}


    // @GetMapping("/{id}")
    // public ResponseEntity<RoleDTO> getRoleById(@PathVariable int id) {
    //     return ResponseEntity.ok(roleService.getRoleById(id));
    // }

    // @PostMapping
    // public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
    //     return ResponseEntity.ok(roleService.createRole(roleDTO));
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteRole(@PathVariable int id) {
    //     roleService.deleteRole(id);
    //     return ResponseEntity.ok().build();
    // }

    // @PutMapping
    // public ResponseEntity<RoleDTO> updateRole(@RequestBody RoleDTO roleDTO) {
    //     return ResponseEntity.ok(roleService.updateRole(roleDTO));
    // }
