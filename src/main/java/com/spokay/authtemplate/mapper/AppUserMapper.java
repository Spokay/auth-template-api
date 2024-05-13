package com.spokay.authtemplate.mapper;

import com.spokay.authtemplate.dto.AppUserResponseDto;
import com.spokay.authtemplate.model.AppUser;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("jwt")
public class AppUserMapper {

    public AppUserResponseDto appUserEntityToResponseDto(AppUser appUserEntity){
        return AppUserResponseDto.builder()
                .id(appUserEntity.getId())
                .email(appUserEntity.getEmail())
                .role(appUserEntity.getRole().name())
                .build();
    }
}
