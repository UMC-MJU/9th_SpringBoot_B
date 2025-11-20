package com.example.umc9th.domain.store.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StoreReqDto {
    public record CreateDto(
            @NotBlank(message = "가게 이름을 입력해 주세요.")
            String storeName,

            @NotNull(message = "가게 번호를 입력해 주세요.")
            Integer storeNumber,

            @NotBlank(message = "주소를 입력해 주세요.")
            String address
    ) {}
}
