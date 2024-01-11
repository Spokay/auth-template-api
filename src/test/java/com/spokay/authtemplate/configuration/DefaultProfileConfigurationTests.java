package com.spokay.authtemplate.configuration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {"spring.profiles.active=default"})
public class DefaultProfileConfigurationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void isDefaultProfileConfigurationLoadedCorrectly() {
        // Arrange
        String[] activeProfiles = context.getEnvironment().getActiveProfiles();
        String[] expectedProfiles = {"default"};

        if (!Arrays.equals(activeProfiles, expectedProfiles)){
            Assertions.fail("The default profile is not loaded");
        }
        // get the beans loaded by the default profile
        ArrayList<String> beanNames = Arrays.stream(context.getBeanDefinitionNames()).collect(Collectors.toCollection(ArrayList::new));

        String expectedAppConfigBeanName = "applicationConfiguration";
        String wrongAppConfigBeanName = "jwtConfiguration";

        String expectedSecurityConfigBeanName = "securityConfiguration";
        String wrongSecurityConfigBeanName = "jwtSecurityConfiguration";

        // assert that the correct config bean is loaded
        Assertions.assertTrue(beanNames.contains(expectedAppConfigBeanName));
        // assert that the wrong config is not loaded
        Assertions.assertFalse(beanNames.contains(wrongAppConfigBeanName));
        // assert that the correct security config is loaded
        Assertions.assertTrue(beanNames.contains(expectedSecurityConfigBeanName));
        // assert that the wrong security config is not loaded
        Assertions.assertFalse(beanNames.contains(wrongSecurityConfigBeanName));
    }
}
