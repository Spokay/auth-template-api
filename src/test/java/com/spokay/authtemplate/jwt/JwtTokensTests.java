package com.spokay.authtemplate.jwt;

import com.spokay.authtemplate.model.AppUser;
import com.spokay.authtemplate.model.Role;
import com.spokay.authtemplate.service.jwt.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(properties = {"spring.profiles.active=jwt"})
public class JwtTokensTests {
    @Autowired
    public PasswordEncoder passwordEncoder;
    @Spy
    public JwtService jwtService;
    public AppUser user;
    public String token;
    public Long expirationTime = 86400000L;

    String secretKey = "493543821869228d750f45a779c2955692b7c8628174773b4f9663fe6b2d51d9";

    @BeforeEach
    void setUp() {
        jwtService = Mockito.spy(new JwtService());
        jwtService.setJwtSecretKey(secretKey);
        jwtService.setJwtExpiration(expirationTime.toString());
        user = AppUser.builder()
                .id(1L)
                .email("test@test.com")
                .pwd(passwordEncoder.encode("password"))
                .role(Role.USER)
                .build();
        token = "testToken";
    }

    @Test
    void isTokenGeneratedCorrectly() {
        String expectedSubject = user.getEmail();
        String generatedToken = jwtService.generateJwtToken(user);

        // assert that getSigningKey is called once
        verify(jwtService, times(1)).getSigningKey();

        long issuedAt = jwtService.extractClaim(generatedToken, Claims::getIssuedAt).getTime();
        long expirationDate = jwtService.extractClaim(generatedToken, Claims::getExpiration).getTime();
        String subject = jwtService.extractClaim(generatedToken, Claims::getSubject);

        // assert that extractClaim is called 3 times
        verify(jwtService, times(3)).extractClaim(anyString(), any());

        // assert that the tokens claims are correct
        assertTrue(issuedAt < expirationDate);
        assertEquals((issuedAt + expirationTime), expirationDate);
        assertEquals(expectedSubject, subject);
    }

    @Test
    void isTokenInvalidWhenTokenExpired(){
        String generatedToken = jwtService.generateJwtToken(user);

        // Manipulate the token's expiration date to be in the past
        int i = generatedToken.lastIndexOf('.');
        String expiredToken = generatedToken.substring(0, i+1) + (System.currentTimeMillis() - 10000) + generatedToken.substring(i+1);

        // assert that the expired token is invalid and throws an exception
        assertThrows(SignatureException.class, () -> jwtService.isTokenExpired(expiredToken));
    }

    @Test
    void isTokenInvalidWhenUserNotFound() {
        String generatedToken = jwtService.generateJwtToken(user);

        // Create a new user with a different username
        AppUser unknownUser = AppUser.builder()
                .id(2L)
                .email("unknown@test.com")
                .pwd(passwordEncoder.encode("password"))
                .role(Role.USER)
                .build();

        // assert that isTokenValid is called once
        boolean isValid = jwtService.isTokenValid(generatedToken, unknownUser);
        verify(jwtService, times(1)).isTokenValid(anyString(), any());

        // assert that the token is not valid for the unknown user
        assertFalse(isValid);
    }

    @AfterEach
    void validate(){
        validateMockitoUsage();
    }
}