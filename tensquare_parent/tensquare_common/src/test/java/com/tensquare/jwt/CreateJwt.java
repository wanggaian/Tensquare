package com.tensquare.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * CreateJwt
 *
 * @Author wanggaian
 * @Date 2019/5/26 16:08
 */
public class CreateJwt {

    public static void main(String[] args) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("888")
                .setSubject("不知名网友")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "itcast")
                .setExpiration(new Date(new Date().getTime() + 60000))
                .claim("role", "admin");
        String str = jwtBuilder.compact();
        System.out.println("jwt token:" + str);
    }
}
