package com.MyGreetingApp.backend.controller.auth;

import com.MyGreetingApp.backend.dto.LoginDto;
import com.MyGreetingApp.backend.dto.UserDto;
import com.MyGreetingApp.backend.dto.LoginResponseDto;
import com.MyGreetingApp.backend.service.auth.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

        if (response.getToken() == null) {
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
    @Operation(summary = "Forgot Password", description = "Allows users to reset their password by providing their email and a new password.")
    @PutMapping("/forgotPassword/{email}")
    public ResponseEntity<String> forgotPassword(@PathVariable String email, @RequestBody Map<String, String> requestBody) {
        String newPassword = requestBody.get("password");
        String response = authService.forgotPassword(email, newPassword);
        return response.startsWith("Password has been changed") ? ResponseEntity.ok(response) : ResponseEntity.badRequest().body(response);
    }

    @Operation(summary = "Reset Password", description = "Allows authenticated users to change their password by providing the current and new password.")
    @PutMapping("/resetPassword/{email}")
    public ResponseEntity<String> resetPassword(@PathVariable String email, @RequestParam String currentPassword, @RequestParam String newPassword) {
        String response = authService.resetPassword(email, currentPassword, newPassword);
        return response.equals("Password reset successfully!") ? ResponseEntity.ok(response) : ResponseEntity.badRequest().body(response);
    }

}
