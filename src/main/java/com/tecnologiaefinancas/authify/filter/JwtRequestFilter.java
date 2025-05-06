package com.tecnologiaefinancas.authify.filter;

import com.tecnologiaefinancas.authify.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.util.List;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    private final JwtUtil jwtUtil;

    private static final List<String> PUBLIC_URLS = List.of("/login", "/register", "/send-reset-otp", "reset-password", "/logout");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();

        if(PUBLIC_URLS.contains(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = null;
        String email = null;

        // 1. Check the authorization header
        final String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt =  authorizationHeader.substring(7);
        }

        // 2. If it is not found in header, check cookies
        if(jwt == null){
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                for(Cookie cookie: cookies){
                    if ("jwt".equals(cookie.getName())){
                        jwt = cookie.getValue();
                        break;
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
