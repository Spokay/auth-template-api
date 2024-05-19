/*
package com.spokay.authtemplate.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@Profile(value = "default")
public class SecurityConfiguration {
    @Value("${enable-csrf:false}")
    private String isCsrfEnabled;

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        if(!Boolean.parseBoolean(isCsrfEnabled)){
            http.csrf(AbstractHttpConfigurer::disable);
        }

        http
            .cors(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
                    requestMatcher -> requestMatcher
                            .anyRequest()
                            .permitAll()
            )
            .formLogin(AbstractHttpConfigurer::disable)
            .sessionManagement(sessionManagementConfigurer ->
                    sessionManagementConfigurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .configure(http)
            )
            .authenticationProvider(authenticationProvider);

        return http.build();
    }
}
*/
