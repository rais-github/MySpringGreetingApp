package com.MyGreetingApp.backend.service.auth;


import com.MyGreetingApp.backend.dto.LoginDto;
import com.MyGreetingApp.backend.dto.LoginResponseDto;
import com.MyGreetingApp.backend.dto.UserDto;

public interface AuthenticationService {
    String registerUser(UserDto userDTO);
    LoginResponseDto loginUser(LoginDto loginDTO);
    String forgotPassword(String email,String newPass);
    String resetPassword(String email, String currentPassword, String newPassword);
}

