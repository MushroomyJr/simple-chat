package com.simple_chat.chat_service.service;

import com.simple_chat.chat_service.entity.User;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import java.util.Date;

@Service
public class JwtService {
    private  final String SECRET_KEY = "818bd7996d71781060650e72b41709bd53be6e7018164d1ff167f225168fffc6f72c0123dd2fab5a433b7ddd89457195537aa12907232ac13a4b9b8c2120410d1b4a47809c94f058a2c1fc5cd50febb476579ac6b7ca105df3060574985bc82d6fd745cf12c210a4910aebed3f83425cdc93fb8a7c0d7ba219dd21d8eefa5c70a834b65e3227fc50f0247e337256ec0025ee6bc8d02fbb61bdb58c544eca6a59873d572f235229ea15aa4c99670bef93f020fb22edc4653d3b9891073efca832b250b78f99ab38d5b684bbdcd72e3b2afba815df86799500204aa26fd2e6c7d9398a042e036b05b714cfe125b9a385fc4468827c74f73564da0358869d8fe85bbc56bf443ce8f1b2fd565c0464ff71b91a990d75ee5e9e532f3f2e8f89f63d5efb280ff3496a471426d5ab22f3b050af2de538997924547a61e7c62e2868ff3ff960f0a48d474ecaa7f433782d503cd9c05e7e0c9d905f8ba8336d8ffb7863aded499e4732e0b5ff282800eb5ad6246351fec4374e4daa8b2393af0672cf3fd1ef26c2ba2db6232d198fe2b18dad880fd764f27c6218d71581c4571369fd7b9d9e2c56040ed2f01f0ff4fae672d87dc85366069be0dcf9d49e7dcbe01a7396a8ce66b2733d65227d1811b2fd18411863e378e9477c0327420688488d86152f25459ee0f8f19374ee09d3109ba4932b10bcb2dfd94709d406f824f0f556e97256";
    private final int JWT_EXPIRATION = 24 * 60 * 60 * 1000;
    private final int REFRESH_EXPIRATION =24 * 60 * 60 * 1000 * 7;
    private final String JWT_ISSUER = "simple_chat";

    public String generateToken (User user){
        return Jwts
                .builder()
                .setSubject(user.getUserName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+JWT_EXPIRATION))
                .setIssuer(JWT_ISSUER)
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                .compact();
    }

    public String generateRefreshToken(User user){
        return Jwts
                .builder()
                .setSubject(user.getUserName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+REFRESH_EXPIRATION))
                .setIssuer(JWT_ISSUER)
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                .compact();
    }
}
