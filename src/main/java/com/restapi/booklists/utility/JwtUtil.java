package com.restapi.booklists.utility;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import java.security.Key;
import java.util.*;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }


    public String generateToken(String email, Set<String> roles){
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        return  Jwts.builder()
                .setSubject(email)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token){
        return Jwts.parser().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
    }

    public Set<String> extractRole(String token){
        return new HashSet<>((List<String>) Jwts.parser().setSigningKey(key()).build().parseClaimsJws(token).getBody().get("role"));
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parser().setSigningKey(key()).build().parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }

    public boolean  validateToken(String token,String email) {
        return email.equals(extractEmail(token)) && !isTokenExpired(token);
    }
}
