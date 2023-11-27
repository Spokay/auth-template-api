package com.spokay.authtemplate.controller;

import com.spokay.authtemplate.dto.AppUserLoginDto;
import com.spokay.authtemplate.dto.AppUserRegisterDto;
import com.spokay.authtemplate.dto.AppUserResponseDto;
import com.spokay.authtemplate.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@CrossOrigin(originPatterns = "*")
public class AuthController {
    private AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<AppUserResponseDto> register(@RequestBody AppUserRegisterDto appUserRegisterDto){
        System.out.println(appUserRegisterDto);
        return authService.register(appUserRegisterDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<AppUserResponseDto> getAllUsers(){
        return authService.getAllUsers();
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AppUserResponseDto> login(@RequestBody AppUserLoginDto appUserLoginDto){
        System.out.println(appUserLoginDto);
        return authService.login(appUserLoginDto);
    }
}
