package com.example.umc9th.domain.review.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ReviewReqDto {
    public record CreateDto(
            @NotNull(message = "리뷰 작성 회원 ID를 입력해주세요.")
            Long memberId,

            @NotNull(message = "별점을 입력해주세요.")
            Integer rating,

            @NotBlank(message = "리뷰 내용을 입력해주세요.")
            String comment,

            List<String> photoUrls
    ) {}
}
