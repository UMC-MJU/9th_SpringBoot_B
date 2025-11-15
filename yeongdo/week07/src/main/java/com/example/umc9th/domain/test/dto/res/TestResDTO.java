package com.example.umc9th.domain.test.dto.res;

import lombok.Builder;
import lombok.Getter;

public class TestResDTO {

    @Builder
    @Getter
    public static class Testing {
        // 파일이 너무 늘어나지 않게 TestResDTO라는 컨테이너 안에
        // 여러 응답을 static class로 정리
        private String testing;
    }

    @Builder
    @Getter
    public static class Exception{
        private String testString;
    }

}
