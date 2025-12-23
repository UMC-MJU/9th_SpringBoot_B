package com.example.umc9th.domain.store.service;

import com.example.umc9th.domain.store.converter.StoreConverter;
import com.example.umc9th.domain.store.dto.req.StoreReqDto;
import com.example.umc9th.domain.store.dto.res.StoreResDto;
import com.example.umc9th.domain.store.entity.Region;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.repository.RegionRepository;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreCommandService {
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Transactional
    public StoreResDto.CreateDto createStore(Long regionId, StoreReqDto.CreateDto dto) {
        // 지역 존재 여부 확인
        Region region = regionRepository.findById(regionId)
                 .orElseThrow(() -> new StoreException(StoreErrorCode.REGION_NOT_FOUND));

        // DTO -> Entity 변환
        Store store = StoreConverter.toStore(dto, region);

        // 저장
        Store savedStore = storeRepository.save(store);

        // Entity -> 응답 DTO 변환
        return StoreConverter.toStoreResDto(savedStore);
    }
}
