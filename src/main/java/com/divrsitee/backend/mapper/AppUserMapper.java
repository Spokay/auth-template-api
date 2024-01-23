package com.divrsitee.backend.mapper;

import com.divrsitee.backend.dto.AppUserResponseDto;
import com.divrsitee.backend.model.AppUser;
import org.springframework.stereotype.Component;

@Component
public class AppUserMapper {

    public AppUserResponseDto appUserEntityToResponseDto(AppUser appUserEntity){
        System.out.println(appUserEntity.getCreatedAt());
        return AppUserResponseDto.builder()
                .id(appUserEntity.getId())
                .firstName(appUserEntity.getFirstName())
                .lastName(appUserEntity.getLastName())
                .username(appUserEntity.getAppUserName())
                .email(appUserEntity.getEmail())
                .seriousGame(appUserEntity.getSeriousGame())
                .role(appUserEntity.getRole().name())
                .createdAt(appUserEntity.getCreatedAt())
                .build();
    }
}
