package com.Elrearning.services;

import com.Elrearning.utils.ProjectProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private  final ProjectProperties properties ;

    public JwtFilter(ProjectProperties properties) {
        this.properties = properties;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            // Extract the token from the Authorization header
            String token = extractToken(request);

            if (token != null) {
                // Validate the token
                Claims claims = Jwts.parser().setSigningKey(properties.getProperty()).parseClaimsJws(token).getBody();
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
