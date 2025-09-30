package com.restapi.booklists.utility;

import com.restapi.booklists.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.util.*;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    private SecretKey key() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }


    public String generateToken(UserEntity user){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());
        return  Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(key())  // ต้องเป็น SecretKey
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject(); // <--- ใช้ได้ตรงนี้
    }

    public String extractRole(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("role", String.class);
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parser().setSigningKey(key()).build().parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }

    public boolean  validateToken(String token,String email) {
        return email.equals(extractEmail(token)) && !isTokenExpired(token);
    }
}
