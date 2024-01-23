package com.divrsitee.backend.service.jwt;

import com.divrsitee.backend.dto.AuthenticationRequestDto;
import com.divrsitee.backend.dto.AuthenticationResponseDto;
import com.divrsitee.backend.dto.RegisterRequestDto;
import com.divrsitee.backend.dto.jwt.JwtAuthenticationResponseDto;
import com.divrsitee.backend.model.AppUser;
import com.divrsitee.backend.model.Role;
import com.divrsitee.backend.repository.AppUserRepository;
import com.divrsitee.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtAuthService implements AuthService {
    private final AppUserRepository appUserRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponseDto register(RegisterRequestDto registerRequestDto){
        var username = (registerRequestDto.getFirstname().charAt(0) + registerRequestDto.getLastname()).replace("-", "").toLowerCase();

        var user = AppUser.builder()
                .firstName(registerRequestDto.getFirstname())
                .lastName(registerRequestDto.getLastname())
                .appUserName(username)
                .email(registerRequestDto.getEmail())
                .pwd(passwordEncoder.encode(registerRequestDto.getPassword()))
                .role(Role.USER)
                .build();
        appUserRepository.save(user);
        var jwtToken = jwtService.generateJwtToken(user);
        return JwtAuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto) throws BadCredentialsException {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequestDto.getUsername(),
                        authenticationRequestDto.getPassword()
                )
        );
        var user = appUserRepository.findAppUserByAppUserName(authenticationRequestDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var jwtToken = jwtService.generateJwtToken(user);

        return JwtAuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }


}
