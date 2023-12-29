package com.spokay.authtemplate.mapper;

import com.spokay.authtemplate.dto.AppUserResponseDto;
import com.spokay.authtemplate.model.AppUser;
import org.springframework.stereotype.Component;

@Component
public class AppUserMapper {

    public AppUserResponseDto appUserEntityToResponseDto(AppUser appUserEntity){
        return AppUserResponseDto.builder()
                .id(appUserEntity.getId())
                .email(appUserEntity.getEmail())
                .role(appUserEntity.getRole().name())
                .build();
    }
}
