package com.wetech.backend_spring_wetech.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(String username, long userId, String role) {
        return JWT.create()
                .withSubject(username)
                .withClaim("id", userId)
                .withClaim("role", role)
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .sign(Algorithm.HMAC512(secret));
    }

    public String getUsernameFromToken(String token) {
        return JWT.require(Algorithm.HMAC512(secret))
                .build()
                .verify(token)
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC512(secret)).build().verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
