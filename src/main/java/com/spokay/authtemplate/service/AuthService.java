package com.spokay.authtemplate.service;

import com.spokay.authtemplate.dto.AuthenticationRequestDto;
import com.spokay.authtemplate.dto.AuthenticationResponseDto;
import com.spokay.authtemplate.dto.DefaultRegisterRequestDto;

public interface AuthService {
    AuthenticationResponseDto register(DefaultRegisterRequestDto registerRequestDto);
    AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto);
}
