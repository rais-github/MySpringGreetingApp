package com.MyGreetingApp.backend.dto;

public class LoginResponseDto {
    private String message;
    private String token;

    public LoginResponseDto(String message, String token) {
        this.message = message;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
}
