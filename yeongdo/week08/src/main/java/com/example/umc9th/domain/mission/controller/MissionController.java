package com.example.umc9th.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MissionController {

    // 미션 도전하기
    @PostMapping("members/{memberId}/missions/{missionId}")
    public
}
