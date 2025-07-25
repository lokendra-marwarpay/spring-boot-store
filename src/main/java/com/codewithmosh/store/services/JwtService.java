package com.codewithmosh.store.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    @Value("${spring.jwt.secret}")
    private String secret;

    public String generateToken(String email) {
        final long expirationTime = 86400; // seconds in a day

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * expirationTime))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }
}
