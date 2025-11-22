package com.example.umc9th.domain.mission.dto.res;

import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMissionId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberMissionResDto {
    private MemberMissionId id;     // 사용자 미션 ID
    private int rewardPoint;        // 보상 포인트
    private String storeName;       // 가게 이름
    private String missionName;     // 미션 이름
    private boolean isCompleted;    // 미션 상태 (진행중/성공)

    public static MemberMissionResDto from(MemberMission memberMission) {
        if (memberMission == null) {
            return null;
        }

        return MemberMissionResDto.builder()
                .id(memberMission.getId())
                .rewardPoint(memberMission.getMission().getRewardPoint())
                .storeName(memberMission.getMission().getStore().getStoreName())
                .missionName(memberMission.getMission().getMissionName())
                .isCompleted(memberMission.isCompleted())
                .build();
    }
}