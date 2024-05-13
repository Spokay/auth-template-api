package com.spokay.authtemplate.model;

import org.springframework.context.annotation.Profile;

@Profile("jwt")
public enum Role {
    USER,
    ADMIN
}
