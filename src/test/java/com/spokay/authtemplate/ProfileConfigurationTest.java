package com.spokay.authtemplate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

//import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProfileConfigurationTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void isDefaultProfileLoadedCorrectly() {
        // Arrange
        String[] activeProfiles = context.getEnvironment().getActiveProfiles();
        String[] expectedProfiles = {"default"};
        // get the beans loaded by the default profile
        ArrayList<String> beanNames = Arrays.stream(context.getBeanDefinitionNames()).collect(Collectors.toCollection(ArrayList::new));

        String expectedAppConfigBeanName = "applicationConfiguration";
        String wrongAppConfigBeanName = "jwtConfiguration";

        String expectedSecurityConfigBeanName = "securityConfiguration";

        // assert the correct profile is loaded
        Assertions.assertArrayEquals(expectedProfiles, activeProfiles);
        // assert that the correct config bean is loaded
        Assertions.assertTrue(beanNames.contains(expectedAppConfigBeanName));
        // assert that the wrong config is not loaded
        Assertions.assertFalse(beanNames.contains(wrongAppConfigBeanName));

    }
}
