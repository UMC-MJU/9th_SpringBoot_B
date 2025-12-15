package com.example.umc9th.global.auth.jwt;

import com.example.umc9th.global.auth.userdetails.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final Duration accessExpiration;

    public JwtUtil(
            @Value("${jwt.token.secretKey}") String secret,
            @Value("%{jwt.token.expiration.access}") Long accessExpiration
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessExpiration = Duration.ofMillis(accessExpiration);
    }

    // AccessToken 생성
    public String createAccessToken(CustomUserDetails user) {
        return createToken(user, accessExpiration);
    }

    // 토근에서 이메일 가져오기
    // @parm token 유저 정보를 추출할 토큰
    // @return 유저 이메일을 토큰에서 추출
    public String getEmail(String token) {
        try {
            return getClaims(token).getPayload().getSubject(); // 파싱해서 subject 가져오기 (createToken에서 이메일을 Subject로 둠)
        } catch (JwtException e) {
            return null;
        }
    }

    // 토큰 유효성 확인
    // @parm token 유효한지 확인할 토큰
    // @return true, false 반환
    public boolean isValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }


    // 토큰 생성
    private String createToken(CustomUserDetails user, Duration expiration) {
        Instant now = Instant.now();

        // 인가 정보
        String authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .subject(user.getUsername()) // User 이메일을 Subject로
                .claim("role", authorities)
                .claim("email", user.getUsername())
                .issuedAt(Date.from(now)) // 발급시간
                .expiration(Date.from(now.plus(expiration))) // 만료시간
                .signWith(secretKey) // 서명
                .compact();
    }

    // 토큰 정보 가져오기
    private Jws<Claims> getClaims(String token) throws JwtException {
        return Jwts.parser()
                .verifyWith(secretKey) // 서명 검증
                .clockSkewSeconds(60) // 서버 시간 오차 60초 허용
                .build()
                .parseSignedClaims(token);
    }
}
