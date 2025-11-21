package com.example.umc9th.domain.review.dto;

import com.example.umc9th.domain.review.entity.Review;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResDto {
    private String storeName;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
    private ReplyResDto reply;

    public static ReviewResDto from(Review review) {
        if (review == null) {
            return null;
        }

        return ReviewResDto.builder()
                .storeName(review.getStore().getStoreName())
                .rating(review.getRating())
                .comment(review.getComment())
                .createdAt(review.getCreatedAt())
                .reply(ReplyResDto.from(review.getReply()))
                .build();
    }
}
