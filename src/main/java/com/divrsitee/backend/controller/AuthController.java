package com.divrsitee.backend.controller;

import com.divrsitee.backend.dto.AuthenticationRequestDto;
import com.divrsitee.backend.dto.AuthenticationResponseDto;
import com.divrsitee.backend.dto.RegisterRequestDto;
import com.divrsitee.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*")
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
