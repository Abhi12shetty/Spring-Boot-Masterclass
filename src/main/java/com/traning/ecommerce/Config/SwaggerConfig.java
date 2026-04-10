package com.traning.ecommerce.Config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        // 1. Define the Security Scheme (Bearer Token)
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .name("Authorization");

        // 2. Require this scheme globally for all endpoints
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("Bearer Authentication");

        // 3. Build and return the OpenAPI object
        return new OpenAPI()
                .info(new Info().title("E-Commerce API").version("1.0").description("API documentation for the E-Commerce Application"))
                .addSecurityItem(securityRequirement)
                .components(new Components().addSecuritySchemes("Bearer Authentication", securityScheme));
    }
}