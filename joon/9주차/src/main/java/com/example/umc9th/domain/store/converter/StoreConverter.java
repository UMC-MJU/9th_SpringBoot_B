package com.example.umc9th.domain.store.converter;

import com.example.umc9th.domain.store.dto.req.StoreReqDto;
import com.example.umc9th.domain.store.dto.res.StoreResDto;
import com.example.umc9th.domain.store.entity.Region;
import com.example.umc9th.domain.store.entity.Store;

public class StoreConverter {
    // DTO -> Entity
    public static Store toStore(StoreReqDto.CreateDto dto, Region region) {
        return Store.builder()
                .storeName(dto.storeName())
                .storeNumber(dto.storeNumber())
                .address(dto.address())
                .region(region)
                .build();
    }

    // Entity -> DTO
    public static StoreResDto.CreateDto toStoreResDto(Store store) {
        return StoreResDto.CreateDto.builder()
                .storeId(store.getId())
                .storeName(store.getStoreName())
                .address(store.getAddress())
                .regionId(store.getRegion().getId())
                .createdAt(store.getCreatedAt())
                .build();
    }
}
