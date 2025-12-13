package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.res.MemberMissionResDto;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface MemberMissionControllerDocs {
    @Operation(
            summary = "사용자가 진행중/완료한 미션 목록 조회 API",
            description = "memberId 기준으로 사용자의 미션 목록을 1 페이지당 10개씩 페이징 조회합니다. page는 1 이상만 허용됩니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "사용자가 도전 및 완료한 미션이 없음")
    })
    ApiResponse<MemberMissionResDto.MemberMissionPageDto> getMyMissions(Long memberId, @ValidPage Integer page);
}
