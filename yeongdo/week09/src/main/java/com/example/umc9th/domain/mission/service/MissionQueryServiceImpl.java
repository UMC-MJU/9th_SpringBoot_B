package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.member.code.MemberErrorCode;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.converter.MissionAssignmentConverter;
import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.MissionResDto;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.MissionAssignment;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import com.example.umc9th.domain.mission.repository.MissionAssignmentRepository;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.store.code.StoreErrorCode;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {
    private final MissionRepository missionRepository;
    private final MissionAssignmentRepository assignmentRepo;
    private final StoreRepository storeRepo;
    private final MemberRepository memberRepo;

    // 내 미션 조회
    @Override
    public Page<MissionResDto.MyMissionPreviewDto> findMyMission (
            Long memberId, MissionStatus status, Pageable pageable
    ) {
        return assignmentRepo.findByMember_IdAndStatusOrderByStartedAtDesc(memberId, status, pageable)
                .map(MissionResDto.MyMissionPreviewDto::of);
    }

    // 가게별 미션 조회
    @Override
    public MissionResDto.StoreMissionListDto findStoreMission(
            String storeName,
            Integer page
    ){
        Store store = storeRepo.findByName(storeName)
                .orElseThrow( () -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // 1부터 시작해버리면 2페이지부터 조회되기 때문
        int zeroBased = page -1;

        PageRequest pageRequest = PageRequest.of(zeroBased, 10);
        Page<Mission> result = missionRepository.findAllByStore(store, pageRequest);

        return MissionConverter.toStoreMissionListDto(result);
    }

    // 멤버별 미션 조회

    @Override
    public MissionResDto.MemberMissionListDto findMemberMission(
            Long memberId,
            Integer page,
            MissionStatus status
    ){
        Member member = memberRepo.findById(memberId)
                .orElseThrow( () -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        int zeroBased = page -1;

        PageRequest pageRequest = PageRequest.of(zeroBased, 10, Sort.by(Sort.Direction.DESC, "startedAt"));

        Page<MissionAssignment> result = assignmentRepo.findByMemberAndStatus(member, status, pageRequest);
        return MissionAssignmentConverter.toMemberMissionListDto(result);
    }
}
