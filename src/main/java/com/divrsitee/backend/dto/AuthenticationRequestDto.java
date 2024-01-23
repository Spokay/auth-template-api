package com.divrsitee.backend.dto;

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
public class AuthenticationRequestDto {
    @NotBlank(message = "Username should not be Blank")
    private String username;

    @NotBlank(message = "Password should not be Blank")
    private String password;
}
