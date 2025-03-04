package com.MyGreetingApp.backend.controller.auth;

import com.MyGreetingApp.backend.dto.LoginDto;
import com.MyGreetingApp.backend.dto.UserDto;
import com.MyGreetingApp.backend.dto.LoginResponseDto;
import com.MyGreetingApp.backend.service.auth.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication Controller", description = "Handles User Authentication")
public class AuthController {

    private final AuthenticationService authService;

    public AuthController(AuthenticationService authService) {
        this.authService = authService;
    }

    @Operation(summary = "User Login", description = "Validates user credentials and returns a JWT token.")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginDto loginDto) {
        LoginResponseDto response = authService.loginUser(loginDto);

        if (response.getToken() == null) { // Login failed
            return ResponseEntity.status(401).body(response);
        }

        return ResponseEntity.ok(response);
    }
    @Operation(summary = "Register a new user", description = "Saves user details and returns success message.")
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        System.out.println("🚀 Register Endpoint Hit: " + userDto.getEmail()); // Debug Log
        String response = authService.registerUser(userDto);
        return ResponseEntity.status(response.startsWith("User registered successfully") ? 201 : 400)
                .body(response);
    }

}
