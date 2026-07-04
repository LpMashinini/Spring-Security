package com.example.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {

    public static final String SECRETS = "c7f4a9d2e1b8f6a3c5d7e9f0a1b2c3d4e5f6a7b8c9d0e1f2a3b4c5d6e7f8a9b0c1d2e3f4a5b6c7d8e9f0a1b2c3d4e5f6";
    public String generateToken(String username){
       return Jwts.builder().
                setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .addClaims(new HashMap<>())
                .signWith(getSignedKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignedKey(){

        return Keys.hmacShaKeyFor(SECRETS.getBytes());
    }

    public Claims verifySignatureAndExtractAllClaims(String token){

        return Jwts.parserBuilder()
                .setSigningKey(getSignedKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token){
       return verifySignatureAndExtractAllClaims(token).getSubject();
    }

    public Date getExpiration(String token){
       return verifySignatureAndExtractAllClaims(token).getExpiration();
    }

    public Boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }


}

