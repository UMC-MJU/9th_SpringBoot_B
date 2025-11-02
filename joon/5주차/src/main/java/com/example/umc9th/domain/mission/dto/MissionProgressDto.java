package com.example.umc9th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionProgressDto {
    private Long memberId;           // 회원 ID
    private Long regionId;           // 지역 ID
    private long completedMissions;  // 완료한 미션 수
}
