package com.divrsitee.backend.dto;

import com.divrsitee.backend.model.SeriousGame;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
@Builder
public class AppUserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private SeriousGame seriousGame;
    private String role;
    private Instant createdAt;
}
