package com.simple_chat.chat_service.service;

import com.simple_chat.chat_service.entity.User;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {
    private final String jwtSecret;
    private final int JWT_EXPIRATION = 24 * 60 * 60 * 1000;
    private final int REFRESH_EXPIRATION =24 * 60 * 60 * 1000 * 7;
    private final String JWT_ISSUER = "simple_chat";

    public JwtService() {
        Dotenv dotenv;
        try {
            dotenv = Dotenv.load();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load .env file. Ensure it exists and is properly configured.", e);
        }
        this.jwtSecret = dotenv.get("JWT_SECRET");
        if (this.jwtSecret == null || this.jwtSecret.isEmpty()) {
            throw new RuntimeException("JWT_SECRET is missing in .env file.");
        }
    }

    public Authentication validateTokenAndExtractAuthentication(String token){
        System.out.println("token: "+token);
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        System.out.println("extracted claims"+ claims);
        String username = claims.getSubject();
        return new UsernamePasswordAuthenticationToken(username, null, List.of());
    }

    public String generateToken (User user){
        return Jwts
                .builder()
                .setSubject(user.getUserName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+JWT_EXPIRATION))
                .setIssuer(JWT_ISSUER)
                .signWith(getSigningKey(),SignatureAlgorithm.HS512)
                .compact();
    }

    public String generateRefreshToken(User user){
        return Jwts
                .builder()
                .setSubject(user.getUserName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+REFRESH_EXPIRATION))
                .setIssuer(JWT_ISSUER)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    private Key getSigningKey(){
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }
}
