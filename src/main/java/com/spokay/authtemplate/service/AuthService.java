package com.spokay.authtemplate.service;

import com.spokay.authtemplate.dto.AuthenticationRequestDto;
import com.spokay.authtemplate.dto.AuthenticationResponseDto;
import com.spokay.authtemplate.dto.RegisterRequestDto;
import com.spokay.authtemplate.model.AppUser;
import com.spokay.authtemplate.model.Role;
import com.spokay.authtemplate.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AppUserRepository appUserRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDto register(RegisterRequestDto registerRequestDto){
        var user = AppUser.builder()
                .email(registerRequestDto.getEmail())
                .pwd(passwordEncoder.encode(registerRequestDto.getPassword()))
                .role(Role.USER)
                .build();
        appUserRepository.save(user);
        var jwtToken = jwtService.generateJwtToken(user);
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequestDto.getEmail(),
                        authenticationRequestDto.getPassword()
                )
        );
        var user = appUserRepository.findAppUserByEmail(authenticationRequestDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var jwtToken = jwtService.generateJwtToken(user);

        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }
}
