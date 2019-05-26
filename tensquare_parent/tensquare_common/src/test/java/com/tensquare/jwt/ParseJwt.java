package com.tensquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

/**
 * ParseJwt
 *
 * @Author wanggaian
 * @Date 2019/5/26 16:18
 */
public class ParseJwt {

    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLkuI3nn6XlkI3nvZHlj4siLCJpYXQiOjE1NTg4NjAwMzAsImV4cCI6MTU1ODg2MDA5MCwicm9sZSI6ImFkbWluIn0.jCNP3FpDdO3ZPfWcgEMPIj2Q_6-5cB4mftfZuXW-loI";
        Claims claims = Jwts.parser().setSigningKey("itcast").parseClaimsJws(token).getBody();
        System.out.println("用户id:" + claims.getId());
        System.out.println("用户名:" + claims.getSubject());
        System.out.println("登录时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getIssuedAt()));
        System.out.println("过期时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getExpiration()));
        System.out.println("用户角色:" + claims.get("role"));
    }
}
