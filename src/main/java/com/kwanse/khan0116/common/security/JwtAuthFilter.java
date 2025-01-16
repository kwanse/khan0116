package com.kwanse.khan0116.common.security;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends GenericFilter {

    private JwtSimpleUtil jwtSimpleUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String authHeader = req.getHeader("Authorization");

        String path = req.getRequestURI();
        if (path.equals("/member/signup") || path.equals("/member/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        // "Bearer 토큰" 형태 가정
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                JwtSimpleUtil.TokenInfo tokenInfo = jwtSimpleUtil.validateAndGetInfo(token);
                req.setAttribute("memberId", tokenInfo.memberId);
                req.setAttribute("role", tokenInfo.role);
            } catch (JwtException e) {
                // 토큰 검증 실패 시
                HttpServletResponse res = (HttpServletResponse) response;
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
