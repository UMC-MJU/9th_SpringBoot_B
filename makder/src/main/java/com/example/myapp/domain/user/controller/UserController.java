package com.example.myapp.domain.user.controller;

import com.example.myapp.global.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Operation(summary = "사용자 정보 조회 API", description = "사용자 정보를 조회하는 API입니다.")
    @GetMapping
    public ApiResponse<String> getUser() {
        return ApiResponse.onSuccess("User");
    }
}
