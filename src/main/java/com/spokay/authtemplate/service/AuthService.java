package com.spokay.authtemplate.service;

import com.spokay.authtemplate.dto.AppUserLoginDto;
import com.spokay.authtemplate.dto.AppUserRegisterDto;
import com.spokay.authtemplate.dto.AppUserResponseDto;
import com.spokay.authtemplate.mapper.AppUserMapper;
import com.spokay.authtemplate.model.AppUser;
import com.spokay.authtemplate.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    AppUserRepository appUserRepository;
    AppUserMapper appUserMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ResponseEntity<AppUserResponseDto> register(AppUserRegisterDto appUserRegisterDto){
        if (appUserRepository.existsByEmail(appUserRegisterDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        AppUser userEntity = appUserMapper.appUserRegisterDtoToEntity(appUserRegisterDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(appUserMapper.appUserEntityToResponseDto(appUserRepository.save(userEntity)));
    }

    public List<AppUserResponseDto> getAllUsers() {
        return appUserRepository.findAll()
                .stream()
                .map(appUserMapper::appUserEntityToResponseDto)
                .toList();
    }

    public ResponseEntity<AppUserResponseDto> login(AppUserLoginDto appUserLoginDto){
        Optional<AppUser> userByEmail = appUserRepository.findAppUserByEmail(appUserLoginDto.getEmail());
        if (userByEmail.isPresent()){
            if (userByEmail.get().getEmail().equals(appUserLoginDto.getEmail()) && passwordEncoder.matches(appUserLoginDto.getPassword(), userByEmail.get().getPassword())){
                AppUserResponseDto body = appUserMapper.appUserEntityToResponseDto(userByEmail.get());
                return ResponseEntity.ok().body(body);
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
