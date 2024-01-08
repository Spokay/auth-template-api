package com.spokay.authtemplate.dto;

import com.spokay.authtemplate.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DefaultAuthenticationResponseDto implements AuthenticationResponseDto {
    private Long id;

    private String email;
    private String role;
}
