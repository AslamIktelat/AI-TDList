package com.ai.tdlist.services;

import com.ai.tdlist.config.ApplicationProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;



@Service
public class JWTService {
    @Autowired
    private ApplicationProperties applicationProperties;
    private final String secretKey;
    private int tokenExpiration;

    @PostConstruct
    public void init() {
        this.tokenExpiration = applicationProperties.getTokenExpiration();
    }
    public JWTService () {

        KeyGenerator keyGen= null;
        try {
            keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secKey =keyGen.generateKey();
            secretKey = java.util.Base64.getEncoder().encodeToString(secKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public String generateToken(String username) {
        HashMap <String, Object> claims = new HashMap<>();
        return  Jwts.builder().claims()
                .add(claims)
                .subject(username)
                .issuedAt(new java.util.Date(System.currentTimeMillis()))
                .expiration(new java.util.Date(System.currentTimeMillis() + tokenExpiration)) // 1 hour
                .and()
                .signWith(getKey())
                .compact();

    }

    private SecretKey getKey() {
        byte [] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        Claims claims = extractAllClaim(token);
        return claims.getSubject();
    }

    private Claims extractAllClaim(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build().parseSignedClaims(token).getPayload();

    }

    public boolean validateToken(String token, UserDetails username) {
       return (extractUsername(token).equals(username.getUsername()));
    }
}
