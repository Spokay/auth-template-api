package com.spokay.authtemplate.util;

import com.spokay.authtemplate.model.AppUser;
import com.spokay.authtemplate.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.JwsAlgorithm;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.algorithm}")
    private String algorithm;

    public AppUser parseToken(String token, JwtDecoder jwtDecoder) throws JwtException {
        try {
            Jwt decodedJwt = jwtDecoder.decode(token);
            String email = decodedJwt.getClaim("email");
            Integer userId = decodedJwt.getClaim("userId");
            String role = decodedJwt.getClaim("role");

            return AppUser.builder()
                    .email(email)
                    .role(role)
                    .id(Long.valueOf(userId))
                    .build();

        } catch (JwtException | NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(AppUser user, JwtEncoder jwtEncoder) {

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .claim("email", user.getEmail())
                .claim("userId", user.getId())
                .claim("role", user.getRole())
                .build();

        JwsAlgorithm jwsAlgorithm = SignatureAlgorithm.from(algorithm);
        JwsHeader header = JwsHeader.with(jwsAlgorithm)
                .type("JWT")
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
    }
}