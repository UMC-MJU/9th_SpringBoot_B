package com.example.umc9th.config;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.member.enums.SocialType;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Region;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.RegionRepository;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final RegionRepository regionRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // 데이터가 이미 있으면 실행하지 않음
        if (memberRepository.count() > 0) {
            return;
        }

        // 1. 지역 데이터 생성
        Region region1 = regionRepository.save(Region.builder().regionName("서울").build());
        Region region2 = regionRepository.save(Region.builder().regionName("부산").build());

        // 2. 멤버 데이터 생성
        Member member1 = memberRepository.save(Member.builder()
                .name("김철수")
                .nickname("철수킴")
                .email("chulsoo@example.com")
                .password("password123")
                .phone("010-1234-5678")
                .birthDate(LocalDate.of(1995, 5, 15))
                .gender(Gender.MALE)
                .socialType(SocialType.KAKAO)
                .socialUid("kakao_12345")
                .build());

        Member member2 = memberRepository.save(Member.builder()
                .name("이영희")
                .nickname("영희리")
                .email("younghee@example.com")
                .password("password456")
                .birthDate(LocalDate.of(1998, 8, 20))
                .gender(Gender.FEMALE)
                .socialType(SocialType.NAVER)
                .socialUid("naver_67890")
                .build());

        // 3. 가게 데이터 생성
        Store store1 = storeRepository.save(Store.builder()
                .storeName("원할머니보쌈")
                .storeNumber(1)
                .address("서울시 강남구")
                .region(region1)
                .build());

        Store store2 = storeRepository.save(Store.builder()
                .storeName("BHC치킨")
                .storeNumber(2)
                .address("부산시 해운대구")
                .region(region2)
                .build());

        // 4. 리뷰 데이터 생성 (김철수가 가게 2곳에 모두 리뷰 작성)
        Review review1 = Review.builder()
                .comment("보쌈이 정말 맛있어요! 또 올게요.")
                .rating(5)
                .member(member1)
                .store(store1)
                .build();

        Review review2 = Review.builder()
                .comment("치킨은 그냥 그랬어요. 조금 짰습니다.")
                .rating(3)
                .member(member1)
                .store(store2)
                .build();
        
        // 5. 리뷰 데이터 생성 (이영희가 가게 1곳에 리뷰 작성)
        Review review3 = Review.builder()
                .comment("족발도 파나요? 보쌈은 괜찮았어요.")
                .rating(4)
                .member(member2)
                .store(store1)
                .build();

        reviewRepository.saveAll(Arrays.asList(review1, review2, review3));

        // 6. 미션 데이터 생성 (원할머니보쌈 가게)
        Mission mission1 = missionRepository.save(Mission.builder()
                .missionName("리뷰 작성하기")
                .rewardPoint(100)
                .deadline(LocalDateTime.now().plusDays(7))
                .store(store1)
                .build());

        Mission mission2 = missionRepository.save(Mission.builder()
                .missionName("포장 주문하기")
                .rewardPoint(50)
                .deadline(LocalDateTime.now().plusDays(10))
                .store(store1)
                .build());

        Mission mission3 = missionRepository.save(Mission.builder()
                .missionName("친구에게 추천하기")
                .rewardPoint(200)
                .deadline(LocalDateTime.now().plusDays(5))
                .store(store1)
                .build());

        Mission mission4 = missionRepository.save(Mission.builder()
                .missionName("사장님과 인사하기")
                .rewardPoint(10)
                .deadline(LocalDateTime.now().minusDays(1)) // 과거 마감일
                .store(store1)
                .build());

        // 7. 멤버-미션 관계 데이터 생성 (김철수)
        // 미션 1: 완료
        MemberMission mission1Completed = MemberMission.createMemberMission(member1, mission1);
        mission1Completed.complete();
        memberMissionRepository.save(mission1Completed);

        // 미션 2: 도전 중
        MemberMission mission2InProgress = MemberMission.createMemberMission(member1, mission2);
        memberMissionRepository.save(mission2InProgress);

        // 미션 3: 도전 가능 (데이터 없음)
        // 미션 4: 마감일 지나서 도전 불가능
    }
}