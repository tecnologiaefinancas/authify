package com.tecnologiaefinancas.authify.filter;

import com.tecnologiaefinancas.authify.service.AppUserDetailsService;
import com.tecnologiaefinancas.authify.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.util.List;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    private final AppUserDetailsService appUserDetailsService;

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
        // 3. Validate the token and set security context
        //TODO: check this part 1:15
     if (jwt != null) {
        email = jwtUtil.extractEmail(jwt);
        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = appUserDetailsService.loadUserByUsername(email);
            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
     }
        filterChain.doFilter(request, response);
    }
}
