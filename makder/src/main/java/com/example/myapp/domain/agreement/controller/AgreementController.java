package com.example.myapp.domain.agreement.controller;

import com.example.myapp.global.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agreements")
public class AgreementController {

    @Operation(summary = "이용약관 조회 API", description = "이용약관을 조회하는 API입니다.")
    @GetMapping
    public ApiResponse<String> getAgreements() {
        return ApiResponse.onSuccess("Agreements");
    }
}
