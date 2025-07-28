package com.codewithmosh.store.services;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.codewithmosh.store.config.JwtConfig;
import com.codewithmosh.store.entities.Role;
import com.codewithmosh.store.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JwtService {
    // @Value("${spring.jwt.secret}")
    // private String secret;
    private final JwtConfig jwtConfig;

    private Jwt generateToken(User user, long expirationTime) {

        var claims = Jwts.claims()
                .subject(user.getId().toString())
                .add("email", user.getEmail())
                .add("name", user.getName())
                .add("role", user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * expirationTime))
                .build();

        return new Jwt(claims, jwtConfig.getSecretKey());
        // return Jwts.builder()
        // .subject(user.getId().toString())
        // .claim("email", user.getEmail())
        // .claim("name", user.getName())
        // .claim("role", user.getRole())
        // .issuedAt(new Date())
        // .expiration(new Date(System.currentTimeMillis() + 1000 * expirationTime))
        // .signWith(jwtConfig.getSecretKey())
        // .compact();
    }

    public Jwt generateAccessToken(User user) {
        return generateToken(user, jwtConfig.getAccessTokenExpiration());
    }

    public Jwt generateRefreshToken(User user) {
        return generateToken(user, jwtConfig.getRefreshTokenExpiration());
    }

    public Jwt parseToken(String token) {
        try {
            var claims = getClaims(token);
            return new Jwt(claims, jwtConfig.getSecretKey());
        } catch (JwtException e) {
            return null;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(jwtConfig.getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // public boolean validateToken(String token) {
    // try {
    // var claims = getClaims(token);
    // return claims.getExpiration().after(new Date());
    // } catch (JwtException e) {
    // return false;
    // }
    // }

    // public Long getUserIdFromToken(String token) {
    // return Long.valueOf(getClaims(token).getSubject());
    // }

    // public Role getRoleFromToken(String token) {
    // return Role.valueOf(getClaims(token).get("role", String.class));
    // }
}
