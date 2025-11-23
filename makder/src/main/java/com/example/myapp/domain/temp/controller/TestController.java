package com.example.myapp.domain.temp.controller;

import com.example.myapp.domain.temp.dto.TestResponseDTO;
import com.example.myapp.domain.temp.service.TestQueryService;
import com.example.myapp.global.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @Operation(summary = "테스트 API", description = "테스트용 API입니다.")
    @GetMapping("/test")
    public ApiResponse<TestResponseDTO> getTest() {
        return ApiResponse.onSuccess(new TestResponseDTO("test success!"));
    }

    @Operation(summary = "예외 발생 테스트 API", description = "예외 발생을 테스트하기 위한 API입니다.")
    @Parameter(name = "flag", description = "1이면 예외 발생")
    @GetMapping("/exception")
    public ApiResponse<TestResponseDTO> getException(@RequestParam Integer flag) {
        testQueryService.checkFlag(flag);
        return ApiResponse.onSuccess(new TestResponseDTO("No exception occurred."));
    }
}
