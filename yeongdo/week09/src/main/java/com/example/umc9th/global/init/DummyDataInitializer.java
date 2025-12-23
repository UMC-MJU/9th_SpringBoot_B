package com.example.umc9th.global.init;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.mission.service.MissionCommandService;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DummyDataInitializer implements CommandLineRunner {

    private final MissionCommandService missionCommandService;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Override
    public void run(String... args) {

        // 1. 테스트 멤버
        Member member = Member.builder()
                .name("영도")
                .email("test@example.com")
                .phoneNumber("01012345678")
                .birth(LocalDate.of(2020, 1, 1))
                .build();

        Member savedMember = memberRepository.save(member);
        System.out.println("== Dummy Member ID: " + savedMember.getId() + " ==");

        // 2. 테스트용 가게 & 미션
        Store store = Store.builder()
                .name("테스트 가게")
                .address1("서울시")
                .build();

        Mission mission = Mission.builder()
                .title("테스트 미션")
                .store(store)
                .title("미션 제목")
                .baseReward(10)
                .build();

        Store savedStore = storeRepository.save(store);
        Mission savedMission = missionRepository.save(mission);

        System.out.println("== Dummy Mission ID: " + savedMission.getId() + " ==");
    }
}
