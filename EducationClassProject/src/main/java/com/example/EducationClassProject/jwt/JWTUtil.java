package com.example.EducationClassProject.jwt;

import com.example.EducationClassProject.apiPayload.code.status.ErrorStatus;
import com.example.EducationClassProject.apiPayload.exception.handler.AuthHandler;
import com.example.EducationClassProject.apiPayload.exception.handler.UserHandler;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JWTUtil {

    private final SecretKey secretKey;
    private final long accessTokenValidityMilliseconds;
    private final UserRepository userRepository;


    public JWTUtil(
            @Value("${spring.jwt.secret}") final String secretKey,
            @Value("${spring.jwt.access-token-time}") final long accessTokenValidityMilliseconds,
            UserRepository userRepository) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.accessTokenValidityMilliseconds = accessTokenValidityMilliseconds;
        this.userRepository = userRepository;
    }

    public String createAccessToken(String email, String role) {
        return createToken(email, role, accessTokenValidityMilliseconds);
    }

    private String createToken(String email, String role, long validityMilliseconds) {
        Claims claims = Jwts.claims();
        claims.put("email", email);
        claims.put("role", role);

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(validityMilliseconds / 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(tokenValidity.toInstant()))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // 토큰에서 이메일 추출
    public String getEmail(String token) {
        return getClaims(token).getBody().get("email", String.class);
    }

    // 토큰 기반으로 user 객체 반환 ( 토큰 유효성 검증 기능 추가 )
    public User getUserFromToken(String token) {

        // 토큰 유효성 검증
        if (!isTokenValid(token)) {
            throw new AuthHandler(ErrorStatus._AUTH_EXPIRE_TOKEN);
        }

        String email = getEmail(token);
        return userRepository.findByEmail(email).orElseThrow(() -> {
            throw new UserHandler(ErrorStatus._NOT_FOUND_USER);
        });
    }

    // 토큰 유효성 검증
    public boolean isTokenValid(String token) {
        try {
            Jws<Claims> claims = getClaims(token);
            Date expiredDate = claims.getBody().getExpiration();
            Date now = new Date();
            return expiredDate.after(now);
        } catch (ExpiredJwtException e) {
            throw new AuthHandler(ErrorStatus._AUTH_EXPIRE_TOKEN);
        } catch (SignatureException
                 | SecurityException
                 | IllegalArgumentException
                 | MalformedJwtException
                 | UnsupportedJwtException e) {
            throw new AuthHandler(ErrorStatus._AUTH_EXPIRE_TOKEN);
        }
    }

    private Jws<Claims> getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
    }
}