package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.res.MissionResDto;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface MissionControllerDocs {
    @Operation(
            summary = "특정 가게의 미션 목록 조회 API",
            description = "storeId 기준으로 해당 가게의 미션 목록을 1 페이지당 10개씩 페이징 조회합니다. page는 1 이상만 허용됩니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "해당 가게에 등록된 미션 없음")
    })
    ApiResponse<MissionResDto.StoreMissionPageDto> getStoreMissions(Long storeId, @ValidPage Integer page);
}
