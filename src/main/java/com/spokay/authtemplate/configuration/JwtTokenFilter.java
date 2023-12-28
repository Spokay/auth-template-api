package com.spokay.authtemplate.configuration;

import com.spokay.authtemplate.service.AuthService;
import com.spokay.authtemplate.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Value("${jwt.header}")
    private final String jwtHeader;

    @Value("${jwt.prefix}")
    private final String jwtPrefix;

    private final AuthService authService;

    /**
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader(jwtHeader);
        String jwtToken;
        if(authHeader == null || !authHeader.startsWith(jwtPrefix)){
            filterChain.doFilter(request, response);
            return;
        }
        jwtToken = authHeader.substring(7);
        String username = jwtService.extractUsername(jwtToken);
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = authService.loadByUsername(username);
            // TODO : generate the token and pass it to the response
        }
        // TODO : Everything else

        filterChain.doFilter(request, response);
    }

}
