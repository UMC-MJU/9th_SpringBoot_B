package com.example.myapp.domain.region.controller;

import com.example.myapp.global.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/regions")
public class RegionController {

    @Operation(summary = "지역 목록 조회 API", description = "지역 목록을 조회하는 API입니다.")
    @GetMapping
    public ApiResponse<String> getRegions() {
        return ApiResponse.onSuccess("Regions");
    }
}
