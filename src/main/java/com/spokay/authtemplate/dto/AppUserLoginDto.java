package com.spokay.authtemplate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AppUserLoginDto {
    @Email(message = "Email pattern is not valid")
    private String email;

    @NotBlank(message = "Password should not be Blank")
    private String password;
}
