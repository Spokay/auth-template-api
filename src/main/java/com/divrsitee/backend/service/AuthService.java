package com.divrsitee.backend.service;

import com.divrsitee.backend.dto.AuthenticationRequestDto;
import com.divrsitee.backend.dto.AuthenticationResponseDto;
import com.divrsitee.backend.dto.RegisterRequestDto;

public interface AuthService {
    AuthenticationResponseDto register(RegisterRequestDto registerRequestDto) throws Exception;
    AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto);
}
