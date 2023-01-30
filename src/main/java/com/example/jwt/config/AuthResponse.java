package com.example.jwt.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private String jwtToken;

    public AuthResponse() {
    }


}