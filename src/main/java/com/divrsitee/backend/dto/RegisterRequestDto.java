package com.divrsitee.backend.dto;

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

    @NotBlank(message = "First name should not be Blank")
    @Size(min = 3, max = 60, message = "First name should be between 3 and 60 characters")
    private String firstname;

    @NotBlank(message = "Last name should not be Blank")
    @Size(min = 3, max = 60, message = "Last name should be between 3 and 60 characters")
    private String lastname;

    @Email(message = "Email pattern is not valid")
    private String email;

   /* @NotBlank(message = "Group id should not be Blank")
    private Long usergroupid;

    @NotBlank(message = "Serious game id should not be Blank")
    private Long seriousgameid;*/

    @NotBlank(message = "Password should not be Blank")
    private String password;
}
