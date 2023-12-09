package com.Elrearning.services;


import java.util.Date;
import java.util.Properties;
import java.util.stream.Collectors;

import com.Elrearning.utils.ProjectProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;


@Service
public class TokenService {


   @Autowired
   private  final ProjectProperties properties ;

    public TokenService(ProjectProperties properties) {
        this.properties = properties;
    }

    public  String generateJwt(Authentication auth){


        Date date = new Date();

        Date expirationDate = new Date(date.getTime()+10000000);




        String scope = auth.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "));




        System.out.println(expirationDate);

        return Jwts.builder()
                .setSubject(auth.getName())
                .claim("Claims","YOU ROCK")
                .setIssuedAt(date)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256,properties.getProperty())
                .compact();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(properties.getProperty()).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(properties.getProperty())
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public String getRoleFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(properties.getProperty())
                .parseClaimsJws(token)
                .getBody();
        return (String) claims.get("roles");
    }

}
