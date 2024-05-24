package com.example.duan.RestController;

import com.example.duan.DTO.request.UserCreationRequest;
import com.example.duan.DTO.request.UserUpdateRequest;
import com.example.duan.DTO.response.ApiResponse;
import com.example.duan.DTO.response.UserResponse;
import com.example.duan.Entity.User;
import com.example.duan.Service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users")
public class UserRestController {
    UserService userService;


    @PostMapping("/create")
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping("/getall")
    public ApiResponse<List<UserResponse>> getUsers(){

        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getAllUsers())
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUser(@PathVariable int id){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(id))
                .build();
    }

    @GetMapping("/myinfo")
    public ApiResponse<UserResponse> getMyInFo(){
        return ApiResponse.<UserResponse>builder()
                .result(userService.myInfo())
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable int id,@RequestBody UserUpdateRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(id,request))
                .build();

    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return "User has been deleted";
    }






}
