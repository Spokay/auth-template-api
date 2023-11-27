package com.spokay.authtemplate.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppUserResponseDto {
    private Long id;
    private String email;
    private String role;
}
