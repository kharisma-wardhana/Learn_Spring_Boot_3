package com.learn.kharis.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.learn.kharis.users.model.AppUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtils {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expire}")
    private Integer jwtExpired;

    @Value("${spring.application.name}")
    private String appName;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token).getClaim("username").asString();
    }

    public String getUserIdFromToken(String token) {
        return getClaimFromToken(token).getSubject();
    }

    public DecodedJWT getClaimFromToken(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
        return verifier.verify(token);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token).getExpiresAt();
    }

    public String generateToken(AppUser userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userDetails.getUsername());
        return doGenerateToken(claims, userDetails.getUsername());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private String doGenerateToken(Map<String, Object> claims, String subject)
            throws IllegalArgumentException, JWTCreationException {
        return JWT.create()
                .withSubject(subject)
                .withPayload(claims)
                .withIssuer(appName)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpired))
                .sign(Algorithm.HMAC256(secret));
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
}


