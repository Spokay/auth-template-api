package com.spokay.authtemplate.service;

import com.spokay.authtemplate.dto.AppUserResponseDto;
import com.spokay.authtemplate.mapper.AppUserMapper;
import com.spokay.authtemplate.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Profile("jwt")
public class AppUserService {
    private final AppUserMapper appUserMapper;

    private final AppUserRepository appUserRepository;

    public List<AppUserResponseDto> getAll() {
        return appUserRepository.findAll()
                .stream()
                .map(appUserMapper::appUserEntityToResponseDto)
                .toList();
    }
}
