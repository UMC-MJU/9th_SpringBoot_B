package com.example.myapp.domain.challenge.controller;

import com.example.myapp.global.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/challenges")
public class ChallengeController {

    @Operation(summary = "챌린지 목록 조회 API", description = "챌린지 목록을 조회하는 API입니다.")
    @GetMapping
    public ApiResponse<String> getChallenges() {
        return ApiResponse.onSuccess("Challenges");
    }
}
