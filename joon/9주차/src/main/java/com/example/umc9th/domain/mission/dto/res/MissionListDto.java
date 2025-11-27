package com.example.umc9th.domain.mission.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MissionListDto {
    private Long missionId;       // 미션 ID
    private String storeName;     // 가게 이름
    private String missionName;   // 미션 이름
    private int rewardPoint;      // 보상 포인트
    private int daysLeft;         // 남은 기한 (D-Day)
}
