package com.spokay.authtemplate.configuration.oauthServer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("oauth-auth-server")
public class OAuthServerConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        http
        .authorizeHttpRequests(
                authorize -> authorize
                .anyRequest()
                .authenticated()
        )
        .oauth2Login(Customizer.withDefaults());
        return http.build();
    }
}