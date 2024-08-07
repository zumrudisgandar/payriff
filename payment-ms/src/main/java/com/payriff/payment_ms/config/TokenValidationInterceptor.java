package com.payriff.payment_ms.config;

import com.payriff.payment_ms.client.SecurityFeignClient;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenValidationInterceptor implements HandlerInterceptor {

    private final SecurityFeignClient securityFeignClient;

    @Autowired
    public TokenValidationInterceptor(@Lazy SecurityFeignClient securityFeignClient) {
        this.securityFeignClient = securityFeignClient;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing or invalid Authorization header");
            return false;
        }

        String token = authHeader.substring(7);

        try {
            System.out.println("Validating token with security-ms: {}" + token);
            securityFeignClient.validateToken(token);
        } catch (Exception e) {
            System.out.println("Token validation failed: {}" + e.getMessage() + e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid token");
            return false;
        }

        return true;
    }
}
