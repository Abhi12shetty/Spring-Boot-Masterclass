package com.traning.ecommerce.Payloads;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
