package com.hospital.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
//import java.util.Base64;





@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration-ms}")
    private long jwtExpirationMs;

    // Key generator
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    // Generate Token
    public String generateToken(Authentication authentication) {

        String username = authentication.getName();

        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    // Extract username
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Validate token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("JWT VALIDATION ERROR: " + e.getMessage());
            return false;
        }
    }
}
























//@Component
//@RequiredArgsConstructor
//public class JwtTokenProvider {
//
//    @Value("${app.jwt.secret}")
//    private String jwtSecret;
//
//    @Value("${app.jwt.expiration-ms}")
//    private long jwtExpirationMs;
//
//    // ✅ Generate token
//    public String generateToken(Authentication authentication) {
//        String username = authentication.getName();
//        Date now = new Date();
//        Date expiry = new Date(now.getTime() + jwtExpirationMs);
//
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(now)
//                .setExpiration(expiry)
//                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
//                .compact();
//    }
//    
//    
//    private Key getSigningKey() {
//        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
//    }
//
//    // ✅ Extract username
//    public String getUsernameFromToken(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(getSigningKey())
//                .build()
//                .parseClaimsJws(token.trim())
//                .getBody()
//                .getSubject();
//    }
//
//    
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder()
//                    .setSigningKey(getSigningKey())
//                    .build()
//                    .parseClaimsJws(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//}
