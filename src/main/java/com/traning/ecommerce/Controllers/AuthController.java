package com.traning.ecommerce.Controllers;

import com.traning.ecommerce.Payloads.AuthRequest;
import com.traning.ecommerce.Security.JwtUtil;
import com.traning.ecommerce.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {

        System.out.println("==========login request for: " + authRequest.getUsername() + "=============");

        // 1. Tell Spring Security to check the DB for this username and password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        // 2. If the password is correct, generate and return the JWT!
        if (authentication.isAuthenticated()) {
            return jwtUtil.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("Invalid user request!");
        }
    }


    //Day 28: Schedulers & Async Operations
    // 1. Inject the service at the top of your Controller
    private final NotificationService notificationService;

    public AuthController(NotificationService notificationService) { // Add to constructor
        this.notificationService = notificationService;
    }

    // 2. Add this test endpoint
    @GetMapping("/test-async")
    public ResponseEntity<String> testAsyncEmail() {

        System.out.println("1. Controller received the request...");

        // This will take 5 seconds to run, but because it's @Async, the controller won't wait!
        notificationService.sendWelcomeEmailBackground("test@user.com");

        System.out.println("2. Controller immediately returning response to Postman!");

        return ResponseEntity.ok("User registered successfully! Check your email.");
    }
}