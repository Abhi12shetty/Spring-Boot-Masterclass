package com.traning.ecommerce.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/sayHello")
    public String sayHellow(){
        System.out.println("Hello World - Abhishek");
        return "Welcome to modern Spring Boot, Junior Dev turned Senior!";
    }
}
