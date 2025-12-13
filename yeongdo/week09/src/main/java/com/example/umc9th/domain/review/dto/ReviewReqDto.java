package com.example.umc9th.domain.review.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public class ReviewReqDto {

    public record CreateReview(
            @NotBlank @Length(max = 20) String title,
            @NotBlank String content,
            @NotNull @DecimalMin("0.0") @DecimalMax("5.0") float rating,
            LocalDateTime createdAt
    ){}

    public record SearchDto(
            @NotNull String storeName,
            @DecimalMin("0.0") @DecimalMax("5.0") Float rating
    ){}
}
