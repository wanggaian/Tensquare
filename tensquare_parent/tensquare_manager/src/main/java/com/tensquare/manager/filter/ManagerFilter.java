package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * ManagerFilter
 *
 * @Author wanggaian
 * @Date 2019/6/1 17:53
 */
@Component
public class ManagerFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 过滤器位置 pre请求前 post后
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器位置 越小越前
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否开启 true开启
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 执行内容 不执行则requestContext.setSendZuulResponse(false);
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {

        System.out.println("zuul manager filter");

        // 获取上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        // 获取请求域
        HttpServletRequest request = requestContext.getRequest();
        if (request.getMethod().equals("OPTIONS")) {
            return null;
        }
        // 登录不拦截
        if (request.getRequestURI().indexOf("admin/login") > 0) {
            System.out.println("管理员登录页面");
            return null;
        }

        // 权限验证
        String header = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(header) && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            if (claims != null) {
                String roles = (String) claims.get("roles");
                if (!StringUtils.isEmpty(roles) && roles.equals("admin")) {
                    requestContext.addZuulRequestHeader("Authorization", header);
                    System.out.println("zuul manager filter admin token check success");
                    return null;
                }
            }
        }
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(403);
        requestContext.setResponseBody("无权访问");
        requestContext.getResponse().setContentType("text/html;charset=UTF-8");

        return null;
    }
}
