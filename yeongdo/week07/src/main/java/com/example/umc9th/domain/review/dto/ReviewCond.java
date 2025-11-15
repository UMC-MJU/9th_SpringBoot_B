package com.example.umc9th.domain.review.dto;

import com.example.umc9th.domain.review.entity.Review;

public record ReviewCond (Long memberId, String storeName, Float rating) {
}
