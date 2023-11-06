package com.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    private static final String HEADER_AUTH = "Authorization";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String token = request.getHeader(HEADER_AUTH);
        if (token != null && jwtTokenUtil.validateToken(token)!=null) {
            return true;
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Need to re-login");
            return false;
        }
    }

}