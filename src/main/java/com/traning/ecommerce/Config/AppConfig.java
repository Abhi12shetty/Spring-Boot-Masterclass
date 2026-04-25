package com.traning.ecommerce.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
//Day 29: Calling Third-Party APIs (RestTemplate)
@Configuration
public class AppConfig {

    // This creates a global RestTemplate we can inject anywhere
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}