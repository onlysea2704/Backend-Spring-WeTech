package com.wetech.backend_spring_wetech.controller;

import com.wetech.backend_spring_wetech.dto.JwtResponse;
import com.wetech.backend_spring_wetech.dto.LoginRequest;
import com.wetech.backend_spring_wetech.dto.RegisterRequest;
import com.wetech.backend_spring_wetech.entity.User;
import com.wetech.backend_spring_wetech.security.JwtTokenProvider;
import com.wetech.backend_spring_wetech.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User user = userService.registerUser(request.getUsername(), request.getPassword());
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        User user = (User) userService.loadUserByUsername(request.getUsername());
        String token = jwtTokenProvider.generateToken(authentication.getName(), user.getUserId(), user.getRole());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/protected")
    public ResponseEntity<?> protectedEndpoint() {
        return ResponseEntity.ok("This is a protected endpoint");
    }
}
