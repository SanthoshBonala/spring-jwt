package com.example.auth.security;

import com.example.auth.domain.CustomUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {


    public String generate(CustomUser customUser) {

        Claims claims = Jwts.claims()
                .setSubject(customUser.getUsername());
        claims.put("userId", String.valueOf(customUser.getId()));
        claims.put("role", customUser.getRoles());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "santhosh")
                .compact();
    }
}
