package com.traning.ecommerce.Config;

import com.traning.ecommerce.Security.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//Day 16: Custom User Authentication & Password Encoding
@Configuration
public class SecurityConfig {

    // 1. ADD THIS NEW BEAN: This exposes the Spring Security Authentication Manager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // This encrypts passwords so hackers can't read them!
    }

    //Day 20: JWT Authentication (Part 3 - The Filter)
    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // Day 19: JWT Authentication (Part 2 - The Login Endpoint)
                        .requestMatchers("/api/auth/login").permitAll()
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
                //Day 20: JWT Authentication (Part 3 - The Filter)
                // --> NEW: Tell Spring to use STATELESS sessions (Don't remember users between requests)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // --> NEW: Put our JwtAuthFilter BEFORE the standard username/password filter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}