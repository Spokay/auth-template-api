package com.spokay.authtemplate.controller;

import com.spokay.authtemplate.dto.AuthenticationRequestDto;
import com.spokay.authtemplate.dto.AuthenticationResponseDto;
import com.spokay.authtemplate.dto.RegisterRequestDto;
import com.spokay.authtemplate.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*")
@Profile("jwt")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthenticationResponseDto> register(@RequestBody @Validated RegisterRequestDto registerRequestDto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerRequestDto));
    }

    @PostMapping({"/authenticate", "/login"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody @Validated AuthenticationRequestDto authenticationRequestDto) {
        return ResponseEntity.ok(authService.authenticate(authenticationRequestDto));
    }

}
