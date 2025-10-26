package com.wetech.backend_spring_wetech.controller;

import com.wetech.backend_spring_wetech.dto.*;
import com.wetech.backend_spring_wetech.entity.Course;
import com.wetech.backend_spring_wetech.entity.User;
import com.wetech.backend_spring_wetech.security.JwtTokenProvider;
import com.wetech.backend_spring_wetech.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
        User user = userService.registerUser(request);
        return ResponseEntity.ok(user);
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

    @PostMapping("/update-profile")
    public ResponseEntity<?> updateProfile(
            @RequestPart("user") UserUpdateRequest user,
            @RequestPart(value = "avatar", required = false) MultipartFile avatar
    ) throws IOException {
        System.out.println(user.toString());
        UserDto updatedUser = userService.updateUser(user, avatar);
        return ResponseEntity.ok("Profile updated");
    }

    @GetMapping("/protected")
    public ResponseEntity<?> protectedEndpoint() {
        return ResponseEntity.ok("This is a protected endpoint");
    }

    @GetMapping("/get-info")
    public ResponseEntity<?> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = (User) userService.loadUserByUsername(username);
        UserDto userDto = new UserDto();
        userDto.setFullname(user.getFullName());
        userDto.setSdt(user.getSdt());
        userDto.setEmail(user.getUsername());
        userDto.setRole(user.getRole());
        userDto.setLinkImage(user.getLinkImage());
        userDto.setUserId(user.getUserId());
        return ResponseEntity.ok(userDto);
    }


}
