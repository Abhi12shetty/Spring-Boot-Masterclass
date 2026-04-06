package com.traning.ecommerce.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//Day 16: Custom User Authentication & Password Encoding
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // This encrypts passwords so hackers can't read them!
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        //Day 17: Role-Based Authorization (RBAC)
                        // 1. Anyone (User or Admin) can view products
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/products/**").hasAnyRole("USER", "ADMIN")

                        // 2. ONLY Admins can Add or Delete products
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/products/**").hasRole("ADMIN")
                        .requestMatchers(org.springframework.http.HttpMethod.DELETE, "/api/products/**").hasRole("ADMIN")
                        .requestMatchers("/api/categories/**").hasRole("ADMIN")

                        // 3. Any other request still needs login
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}