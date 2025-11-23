package com.example.myapp.domain.food.controller;

import com.example.myapp.global.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foods")
public class FoodController {

    @Operation(summary = "음식 카테고리 조회 API", description = "음식 카테고리를 조회하는 API입니다.")
    @GetMapping
    public ApiResponse<String> getFoodCategories() {
        return ApiResponse.onSuccess("Food Categories");
    }
}
