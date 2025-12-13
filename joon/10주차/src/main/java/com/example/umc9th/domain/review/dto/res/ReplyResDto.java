package com.example.umc9th.domain.review.dto;

import com.example.umc9th.domain.review.entity.Reply;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyResDto {
    private String content;
    private LocalDateTime createdAt;

    public static ReplyResDto from(Reply reply) {
        if (reply == null) {
            return null;
        }

        return ReplyResDto.builder()
                .content(reply.getContent())
                .createdAt(reply.getCreatedAt())
                .build();
    }
}
