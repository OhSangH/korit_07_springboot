package com.example.todolist.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtService {
    static final long EXPIRATION_TIME = 864_000_000;
    static final String PREFIX = "Bearer ";

    static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 서명이 이루어진 JWT 토큰을 생성
    public String getToken(String username){
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)      // 미리 생성한 비밀키로 서명
                .compact();

        return  token;
    }


    public String getAuthUser(HttpServletRequest request){
        String token = request.getHeader(
                // 이 클래스의 객체가 정확히 뭔지 모르겠지만 method 명을 봤을 때 Header를 가지고  온다는 것을 알 수 있다.
                // 여기 Header는 postman에서 볼 수 있는 headers에 해당한다.
                HttpHeaders.AUTHORIZATION
        );
        if(token != null ){
            String user = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token.replace(PREFIX,""))
                    .getBody()
                    .getSubject();

            if (user != null) {
                return user;
            }
            return null;
        }
        return null;
    }

}
