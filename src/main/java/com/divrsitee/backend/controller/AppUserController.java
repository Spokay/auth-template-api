package com.divrsitee.backend.controller;

import com.divrsitee.backend.dto.AppUserResponseDto;
import com.divrsitee.backend.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<AppUserResponseDto> getAllUsers(){
        return appUserService.getAll();
    }
}
