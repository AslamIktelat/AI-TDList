package com.ai.tdlist.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@Service
public class JWTService {
    private String secretKey = "";
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
                .expiration(new java.util.Date(System.currentTimeMillis() + 60 * 60 * 10))
                .and()
                .signWith(getKey())
                .compact();

    }

    private Key getKey() {
        byte [] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
