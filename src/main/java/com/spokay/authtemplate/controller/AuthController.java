package com.spokay.authtemplate.controller;

import com.spokay.authtemplate.dto.AuthenticationRequestDto;
import com.spokay.authtemplate.dto.AuthenticationResponseDto;
import com.spokay.authtemplate.dto.RegisterRequestDto;
import com.spokay.authtemplate.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthenticationResponseDto> register(@RequestBody RegisterRequestDto registerRequestDto){
        System.out.println(registerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerRequestDto));
    }

    @PostMapping({"/authenticate", "/login"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody AuthenticationRequestDto authenticationRequestDto){
        System.out.println(authenticationRequestDto);
        return ResponseEntity.ok(authService.authenticate(authenticationRequestDto));
    }

}
