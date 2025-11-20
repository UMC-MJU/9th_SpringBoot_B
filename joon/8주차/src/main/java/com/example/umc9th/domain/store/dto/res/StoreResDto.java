package com.example.umc9th.domain.store.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

public class StoreResDto {
    @Builder
    public record CreateDto(
       Long storeId,
       String storeName,
       Integer storeNumber,
       String address,
       Long regionId,
       LocalDateTime createdAt
    ) {}
}
