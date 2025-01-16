package com.kwanse.khan0116.security;

import com.kwanse.khan0116.member.application.TokenPublisher;
import com.kwanse.khan0116.member.domain.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtSimpleUtil implements TokenPublisher {

    private Key key;
    private final long expirationTime;

    public JwtSimpleUtil(
            @Value("${jwt.secret_key}") final String secretKey,
            @Value("${jwt.expiration}") final long expirationTime
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);

        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.expirationTime = expirationTime;
    }

    @Override
    public String createToken(Long memberId, Role role) {
        return Jwts.builder()
                .setSubject(memberId.toString())
                .claim("role", role.name())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    public TokenInfo validateAndGetInfo(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();
        String role = claims.get("role", String.class);
        return new TokenInfo(username, role);
    }

    public static class TokenInfo {
        public String memberId;
        public String role;
        public TokenInfo(String memberId, String role) {
            this.memberId = memberId;
            this.role = role;
        }
    }}
