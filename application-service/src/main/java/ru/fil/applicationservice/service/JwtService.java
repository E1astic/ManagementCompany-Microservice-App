package ru.fil.applicationservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey getSecretAsKey(String secret) {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    private String extractTokenFromHeader(String header) {
        return header.substring(7);
    }

    private Claims getAllClaimsFromHeader(String header) {
        String token = extractTokenFromHeader(header);
        return Jwts.parser()
                .verifyWith(getSecretAsKey(secret))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Integer getUserIdFromHeader(String header) {
        return getAllClaimsFromHeader(header).get("userId", Integer.class);
    }
}
