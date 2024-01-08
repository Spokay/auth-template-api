package com.spokay.authtemplate.service;

import com.spokay.authtemplate.dto.AuthenticationRequestDto;
import com.spokay.authtemplate.dto.AuthenticationResponseDto;
import com.spokay.authtemplate.dto.DefaultAuthenticationResponseDto;
import com.spokay.authtemplate.dto.RegisterRequestDto;
import com.spokay.authtemplate.model.AppUser;
import com.spokay.authtemplate.model.Role;
import com.spokay.authtemplate.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Profile(value = "default")
public class DefaultAuthService implements AuthService {
    private final AppUserRepository appUserRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDto register(RegisterRequestDto registerRequestDto) throws Exception {
        if(!appUserRepository.existsByEmail(registerRequestDto.getEmail())){
            var user = AppUser.builder()
                    .email(registerRequestDto.getEmail())
                    .pwd(passwordEncoder.encode(registerRequestDto.getPassword()))
                    .role(Role.USER)
                    .build();
            appUserRepository.save(user);
            return DefaultAuthenticationResponseDto.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .role(user.getRole().name())
                    .build();
        }else{
            throw new Exception("User already exists");
        }
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto) throws AuthenticationException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequestDto.getEmail(),
                        authenticationRequestDto.getPassword()
                )
        );
        var user = appUserRepository.findAppUserByEmail(authenticationRequestDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return DefaultAuthenticationResponseDto.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .role(user.getRole().name())
                    .build();
    }
}
