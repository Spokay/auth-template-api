package com.spokay.authtemplate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    @NotBlank(message = "Email should not be Blank")
    @Size(min = 1, max = 255, message = "Email should be between 1 and 255 characters")
    @Email(message = "Email pattern is not valid")
    private String email;

    @NotBlank(message = "Password should not be Blank")
    private String password;
}
