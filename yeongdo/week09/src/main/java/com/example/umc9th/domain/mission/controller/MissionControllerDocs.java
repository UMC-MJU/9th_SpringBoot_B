package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionReqDto;
import com.example.umc9th.domain.mission.dto.MissionResDto;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


public interface MissionControllerDocs {

    @Operation(
            summary = "미션 시작 API By 영도",
            description = "유저가 미션을 새로 시작하고, 관련 정보를 반환합니다."
    )
    ApiResponse<MissionResDto.StartDto> startMission(
            Long memberId,
            Long missionId,
            MissionReqDto.StartDto dto
    );

    @Operation(
            summary = "가게별 미션을 모두 조회 API By 영도",
            description = "가게별로 해당하는 미션들을 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<MissionResDto.StoreMissionListDto> getMissions(
            String storeName,
            Integer page
    );
}
