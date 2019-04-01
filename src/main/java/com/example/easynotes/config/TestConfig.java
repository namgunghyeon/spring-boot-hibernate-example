package com.example.easynotes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
public class TestConfig {

    @Autowired
    private Environment env;

    public void testConfigRun() {
        System.out.println("testConfigRun");
        System.out.println(env);
    }

}
