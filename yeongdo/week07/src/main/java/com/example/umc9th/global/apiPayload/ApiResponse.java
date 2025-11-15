package com.example.umc9th.global.apiPayload;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private boolean success;

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("result")
    private T result; // 인스턴스 자체가 T 타입을 가지니까 클래스 레벨에서도 제너릭이 필요

    // 앞의 <T> -> "이 메서드는 제너릭 메서드다."
    // 뒤의 ApiResponse<T> : "이 메서드는 ApiResponse<T> 타입을 리턴한다" (선언한 T를 실제로 쓰는 부분)
    // ->  "T 타입을 받는 제너릭 메서드 onFailure인데, T타입의 result를 받아서 ApiResponse<T>를 리턴한다."

    // 성공 시
    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code, T result) {
        return new ApiResponse<>(true, code.getCode(), code.getMessage(), result);
    }
    // 실패 시
    public static <T> ApiResponse<T> onFailure (BaseErrorCode code, T result) {
        return new ApiResponse<>(false, code.getCode(), code.getMessage(), result);
    }

}
