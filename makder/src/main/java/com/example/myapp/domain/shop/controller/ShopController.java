package com.example.myapp.domain.shop.controller;

import com.example.myapp.global.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shops")
public class ShopController {

    @Operation(summary = "가게 목록 조회 API", description = "가게 목록을 조회하는 API입니다.")
    @GetMapping
    public ApiResponse<String> getShops() {
        return ApiResponse.onSuccess("Shops");
    }
}
