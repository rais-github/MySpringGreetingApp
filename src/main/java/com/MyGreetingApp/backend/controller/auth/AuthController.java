package com.MyGreetingApp.backend.controller.auth;

import com.MyGreetingApp.backend.dto.LoginDto;
import com.MyGreetingApp.backend.dto.UserDto;
import com.MyGreetingApp.backend.dto.LoginResponseDto;
import com.MyGreetingApp.backend.service.auth.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authService;

    public AuthController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginDto loginDto) {
        LoginResponseDto response = authService.loginUser(loginDto);

        if (response.getToken() == null) { // Login failed
            return ResponseEntity.status(401).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        System.out.println("🚀 Register Endpoint Hit: " + userDto.getEmail()); // Debug Log
        String response = authService.registerUser(userDto);
        return ResponseEntity.status(response.startsWith("User registered successfully") ? 201 : 400)
                .body(response);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("✅ Public endpoint is working!");
    }
}
