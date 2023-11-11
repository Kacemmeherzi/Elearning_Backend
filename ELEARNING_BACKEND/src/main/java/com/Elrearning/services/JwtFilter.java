package com.Elrearning.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {
    String secret = "Au+HOLt8D1G8s2N/Frv/BL/edOHij7qljXWmYG1i44vJhCassNlMkT7guItWkiwA+YQjpGSGnKwPfYWYjixGOV6W4ujMEaCY+UsFm0esXzAsyCgSwiy4x9g20jr/wwTvYyTNXBmrj6Qj1GW8ZGsjL17cbMT7+f/niVU7vRWfovD1onI1pWBP+g7rXtR7XQR6M6BY/NMfZsxYxj5FWbN+GSEmkW/DlvXS1b/VCw7xd6VEluGX8M7viJrxCE06AhOQPNRcP4DhZGUlTwH8pbulPnLoZeBs/x1HH4ysSZ2mMwgAqI3c39YK1unXu79wUgUMpR0JoinVuQUc/kc6EMqHqjljRx4IbwwZ1TdezEv4s68=";

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            // Extract the token from the Authorization header
            String token = extractToken(request);

            if (token != null) {
                // Validate the token
                Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
                String username = claims.getSubject();

                // Create an Authentication object
                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, null);

                // Set the Authentication in the SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (SignatureException e) {
            // Handle token signature exception
            // For simplicity, you might want to log the exception and proceed without setting up authentication
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }

        return null;
    }


}
