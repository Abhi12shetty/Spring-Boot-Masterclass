package com.traning.ecommerce.Controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    // This maps to the exact root URL where Google redirects you!
    @GetMapping("/")
    public String home(@AuthenticationPrincipal OAuth2User principal) {

        // If the user is logged in via Google, extract their data
        if (principal != null) {
            String name = principal.getAttribute("name");
            String email = principal.getAttribute("email");

            return "<h1>Welcome to the E-Commerce API!</h1>" +
                    "<p>Successfully logged in via Google.</p>" +
                    "<p><b>Name:</b> " + name + "</p>" +
                    "<p><b>Email:</b> " + email + "</p>";
        }

        return "Welcome! Please log in.";
    }
}