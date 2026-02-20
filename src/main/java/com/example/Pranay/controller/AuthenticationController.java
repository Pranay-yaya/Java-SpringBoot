package com.example.Pranay.controller;

import com.example.Pranay.Authentication.JwtUtil;
import com.example.Pranay.dto.LoginRequestDto;
import com.example.Pranay.dto.UserDto;
import com.example.Pranay.entity.Token;
import com.example.Pranay.entity.User;
import com.example.Pranay.entity.VerificationStatus;
import com.example.Pranay.repository.TokenRepository;
import com.example.Pranay.repository.UserRepository;
import com.example.Pranay.service.UserService;
import jakarta.mail.MessagingException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    private final JwtUtil jwtUtil;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    private UserService userService;


    public AuthenticationController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto request){
        User user = (User) userRepository.findByUsername(request.getUsername());
        if(user.getPassword().equals(request.getPassword())){
            String token=jwtUtil.generateToken(
                    request.getUsername(),
                    "ROLE_USER"
            );
            return ResponseEntity.ok(token);

        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto){
        try{
            userService.createUser(userDto);
            return ResponseEntity.ok("User registered successfully");
        } catch (MessagingException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }



    @GetMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam String token){

        Token t=tokenRepository.findByToken(token);
        User user=t.getUser();
        user.getUserInfo().setVerificationStatus(VerificationStatus.VERIFIED);

        return  ResponseEntity.ok("User is verified");
    }
}
