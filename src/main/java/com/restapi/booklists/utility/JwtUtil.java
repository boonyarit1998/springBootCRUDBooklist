package com.restapi.booklists.utility;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    private final SecretKey secretKey = Keys.hmacShaKeyFor("my-super-secret-key-which-is-very-long".getBytes());

    public String generateToken(String email, String role){
        return  Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(secretKey)
                .compact();
    }

    public void validateToken(String token) {
        try {
            Jwts.parser().verifyWith((SecretKey) secretKey)
                    .build()
                    .parseSignedClaims(token);
        } catch (SignatureException e) {
            throw new JwtException("Invalid JWT signature");
        } catch (JwtException e) {
            throw new JwtException("Invalid JWT");
        }
    }
}
