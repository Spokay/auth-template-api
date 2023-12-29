package com.spokay.authtemplate.dto;

import com.spokay.authtemplate.model.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppUserResponseDto {
    private Long id;
    private String email;
    private String role;
}
