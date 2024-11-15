package com.example.iot_project_backserver.Security.Config.Jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 모든 헤더를 출력
        System.out.println("전체 요청 헤더:");
        request.getHeaderNames().asIterator().forEachRemaining(headerName ->
                System.out.println(headerName + ": " + request.getHeader(headerName))
        );

        // Authorization 헤더의 값 조회
        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        System.out.println("Authorization 헤더: " + authorizationHeader);

        // 가져온 값에서 접두사 제거
        String token = getAccessToken(authorizationHeader);
        System.out.println("추출된 토큰: " + token);

        // 토큰 유효성 검증
        if (token != null && tokenProvider.validateToken(token)) {
            System.out.println("토큰이 유효합니다. SecurityContext에 인증 정보 설정 중...");
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            System.out.println("토큰이 유효하지 않거나 존재하지 않습니다.");
        }

        filterChain.doFilter(request, response);
    }

    private String getAccessToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}
