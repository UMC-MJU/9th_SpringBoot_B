package com.example.myapp.domain.temp.controller;

import com.example.myapp.domain.temp.dto.TestResponseDTO;
import com.example.myapp.domain.temp.service.TestQueryService;
import com.example.myapp.global.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TestController {

    private final TestQueryService testQueryService;

    @GetMapping("/test")
    public ApiResponse<TestResponseDTO> getTest() {
        return ApiResponse.onSuccess(new TestResponseDTO("test success!"));
    }

    @GetMapping("/exception")
    public ApiResponse<TestResponseDTO> getException(@RequestParam Integer flag) {
        testQueryService.checkFlag(flag);
        return ApiResponse.onSuccess(new TestResponseDTO("No exception occurred."));
    }
}
