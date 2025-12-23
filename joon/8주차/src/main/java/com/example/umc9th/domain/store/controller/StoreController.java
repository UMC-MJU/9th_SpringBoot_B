package com.example.umc9th.domain.store.controller;

import com.example.umc9th.domain.store.dto.req.StoreReqDto;
import com.example.umc9th.domain.store.dto.res.StoreResDto;
import com.example.umc9th.domain.store.exception.code.StoreSuccessCode;
import com.example.umc9th.domain.store.service.StoreCommandService;
import com.example.umc9th.global.annotation.ExistRegion;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreCommandService storeCommandService;

    // 특정 지역에 가게 추가 API
    @PostMapping("/regions/{regionId}/stores/register")
    public ApiResponse<StoreResDto.CreateDto> createStore(@PathVariable @ExistRegion Long regionId, @RequestBody @Valid StoreReqDto.CreateDto dto) {
        StoreResDto.CreateDto response = storeCommandService.createStore(regionId, dto);
        return ApiResponse.onSuccess(StoreSuccessCode.CREATED, response);
    }
}
