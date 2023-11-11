package com.Elrearning.services;


import java.util.Date;
import java.util.stream.Collectors;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;


@Service
public class TokenService {
    


    static final String KEY = "Au+HOLt8D1G8s2N/Frv/BL/edOHij7qljXWmYG1i44vJhCassNlMkT7guItWkiwA+YQjpGSGnKwPfYWYjixGOV6W4ujMEaCY+UsFm0esXzAsyCgSwiy4x9g20jr/wwTvYyTNXBmrj6Qj1GW8ZGsjL17cbMT7+f/niVU7vRWfovD1onI1pWBP+g7rXtR7XQR6M6BY/NMfZsxYxj5FWbN+GSEmkW/DlvXS1b/VCw7xd6VEluGX8M7viJrxCE06AhOQPNRcP4DhZGUlTwH8pbulPnLoZeBs/x1HH4ysSZ2mMwgAqI3c39YK1unXu79wUgUMpR0JoinVuQUc/kc6EMqHqjljRx4IbwwZ1TdezEv4s68=";

    public static String generateJwt(Authentication auth){
        Date date = new Date();

        Date expirationDate = new Date(date.getTime()+10000000);




        String scope = auth.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "));




        System.out.println(expirationDate);

        return Jwts.builder()
                .setSubject(auth.getName())
                .claim("Claims","YOU ROCKK")
                .setIssuedAt(date)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();
    }

}
