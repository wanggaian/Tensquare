package com.tensquare.user.interceptor;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JwtInterceptor
 *
 * @Author wanggaian
 * @Date 2019/5/26 17:55
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过拦截器");

        String header = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(header)) {
            if (header.startsWith("Bearer ")) {
                // get token
                String token = header.substring(7);
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if (!StringUtils.isEmpty(roles) && roles.equals("admin")) {
                        request.setAttribute("claims_admin", token);
                    }
                    if (!StringUtils.isEmpty(roles) && roles.equals("user")) {
                        request.setAttribute("claims_user", token);
                    }

                } catch (Exception e) {
                    throw new RuntimeException("无效令牌");
                }
            }
        }
        return true;
    }
}
