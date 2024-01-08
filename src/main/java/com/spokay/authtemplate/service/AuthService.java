package com.spokay.authtemplate.service;

import com.spokay.authtemplate.dto.AuthenticationRequestDto;
import com.spokay.authtemplate.dto.AuthenticationResponseDto;
import com.spokay.authtemplate.dto.RegisterRequestDto;

public interface AuthService {
    AuthenticationResponseDto register(RegisterRequestDto registerRequestDto) throws Exception;
    AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto);
}
