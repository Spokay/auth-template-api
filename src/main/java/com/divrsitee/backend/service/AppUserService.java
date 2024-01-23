package com.divrsitee.backend.service;

import com.divrsitee.backend.dto.AppUserResponseDto;
import com.divrsitee.backend.mapper.AppUserMapper;
import com.divrsitee.backend.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
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
