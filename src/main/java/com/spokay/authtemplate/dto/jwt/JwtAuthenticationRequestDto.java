package com.spokay.authtemplate.dto.jwt;

import com.spokay.authtemplate.dto.AuthenticationRequestDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthenticationRequestDto implements AuthenticationRequestDto {
    @Email(message = "Email pattern is not valid")
    private String email;

    @NotBlank(message = "Password should not be Blank")
    private String password;
}