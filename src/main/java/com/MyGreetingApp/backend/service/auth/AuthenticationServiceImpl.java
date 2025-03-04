package com.MyGreetingApp.backend.service.auth;

import com.MyGreetingApp.backend.dto.LoginDto;
import com.MyGreetingApp.backend.dto.LoginResponseDto;
import com.MyGreetingApp.backend.dto.UserDto;

import com.MyGreetingApp.backend.model.User;
import com.MyGreetingApp.backend.repository.UserRepository;
import com.MyGreetingApp.backend.service.EmailService;
import com.MyGreetingApp.backend.util.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final EmailService emailService;

    public AuthenticationServiceImpl(EmailService emailService,UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.emailService=emailService;
    }

    @Override
    public String registerUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            return "Email is already in use.";
        }

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(user);


        String subject = "Welcome to MyGreetingApp!";
        String message = "Hello " + user.getFirstName() + ", welcome to our application!\n\n"
                + "Please find the attached user guide.";


        String attachmentPath = "src/main/resources/static/Himanshu_Rai_CV.pdf";

        if (attachmentPath == null || attachmentPath.isEmpty()) {

            emailService.sendEmail(user.getEmail(), subject, message, user.getEmail());
        } else {

            emailService.sendEmailWithAttachment(user.getEmail(), subject, message, attachmentPath, user.getEmail());
        }

        return "User registered successfully!";
    }




    @Override

    public LoginResponseDto loginUser(LoginDto loginDto) {
        Optional<User> userOptional = userRepository.findByEmail(loginDto.getEmail());

        if (userOptional.isEmpty()) {
            return new LoginResponseDto("User not found!", null);
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            return new LoginResponseDto("Invalid email or password!", null);
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new LoginResponseDto("Login successful!", token);
    }

}
