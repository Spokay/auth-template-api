package com.spokay.authtemplate.mapper;

import com.spokay.authtemplate.dto.AppUserRegisterDto;
import com.spokay.authtemplate.dto.AppUserResponseDto;
import com.spokay.authtemplate.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AppUserMapper {
    @Autowired
    PasswordEncoder passwordEncoder;
    public AppUser appUserRegisterDtoToEntity(AppUserRegisterDto appUserRegisterDto){
        return AppUser.builder()
                        .email(appUserRegisterDto.getEmail())
                        .password(passwordEncoder.encode(appUserRegisterDto.getPassword()))
                        .role("USER")
                        .build();
    }

    public AppUserResponseDto appUserEntityToResponseDto(AppUser appUserEntity){
        return AppUserResponseDto.builder()
                .id(appUserEntity.getId())
                .email(appUserEntity.getEmail())
                .role(appUserEntity.getRole())
                .build();
    }
}
