package com.divrsitee.backend.dto.jwt;

import com.divrsitee.backend.dto.AuthenticationResponseDto;
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