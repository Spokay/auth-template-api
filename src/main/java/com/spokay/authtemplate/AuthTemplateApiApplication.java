package com.spokay.authtemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;
import java.util.Objects;

@SpringBootApplication
public class AuthTemplateApiApplication {

    @Autowired
    ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(AuthTemplateApiApplication.class, args);
    }


    /*@Bean
    public CommandLineRunner getEndpointsInfos() {
        return args -> {
        };
    }*/
}
