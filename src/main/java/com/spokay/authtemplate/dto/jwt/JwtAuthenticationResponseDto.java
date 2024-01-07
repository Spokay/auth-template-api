package com.spokay.authtemplate.dto.jwt;

import com.spokay.authtemplate.dto.AuthenticationResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthenticationResponseDto implements AuthenticationResponseDto {
    String token;
}