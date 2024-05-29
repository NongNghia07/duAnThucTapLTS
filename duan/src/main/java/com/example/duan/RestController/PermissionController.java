package com.example.duan.RestController;

import com.example.duan.DTO.request.PermissionRequest;
import com.example.duan.DTO.response.ApiResponse;
import com.example.duan.DTO.response.PermissionResponse;
import com.example.duan.Service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import com.example.duan.DTO.PermissionDTO;
import com.example.duan.Service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/permission")
public class PermissionController {

    PermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request){
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll(){
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .build();
    }

    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable String permission){
        permissionService.delete(permission);
        return ApiResponse.<Void>builder().build();
    }

    // @GetMapping
    // public ResponseEntity<List<PermissionDTO>> getAllPermissions() {
    //     return ResponseEntity.ok(permissionService.getAllPermissions());
    // }

    // @GetMapping("/{id}")
    // public ResponseEntity<PermissionDTO> getPermissionById(@PathVariable int id) {
    //     return ResponseEntity.ok(permissionService.getPermissionById(id));
    // }

    // @PostMapping
    // public ResponseEntity<List<PermissionDTO>> createPermission(@RequestBody List<PermissionDTO> permissionDTOs) {
    //     return ResponseEntity.ok(permissionService.createPermission(permissionDTOs));
    // }

    // @DeleteMapping
    // public ResponseEntity<Void> deletePermission(@RequestParam int id) {
    //     permissionService.deletePermission(id);
    //     return ResponseEntity.ok().build();
    // }

    // @PutMapping
    // public ResponseEntity<PermissionDTO> updatePermission(@RequestBody PermissionDTO permissionDTO) {
    //     return ResponseEntity.ok(permissionService.updatePermission(permissionDTO));
    // }
}
