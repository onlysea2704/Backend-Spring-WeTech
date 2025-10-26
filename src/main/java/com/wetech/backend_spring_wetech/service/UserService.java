package com.wetech.backend_spring_wetech.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.wetech.backend_spring_wetech.dto.RegisterRequest;
import com.wetech.backend_spring_wetech.dto.UserDto;
import com.wetech.backend_spring_wetech.dto.UserUpdateRequest;
import com.wetech.backend_spring_wetech.entity.Course;
import com.wetech.backend_spring_wetech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.wetech.backend_spring_wetech.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private Cloudinary cloudinary;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole("USER");
        user.setSdt(registerRequest.getPhone());
        user.setCreated(new Date());
        user.setFullName(registerRequest.getFullName());
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return (User) loadUserByUsername(username);
    }

    public UserDto updateUser(UserUpdateRequest user, MultipartFile image) throws IOException {

        String imageUrl = user.getLinkImage();
        if(image != null){
            imageUrl = uploadToCloudinary(image);
        }
        Optional<User> optionalUser = userRepository.findByUsername(user.getEmail());
        if(optionalUser.isPresent()){
            User existingUser = optionalUser.get();
            existingUser.setLinkImage(imageUrl);
            existingUser.setFullName(user.getFullName());
            existingUser.setSdt(user.getPhone());
            User updatedUser = userRepository.save(existingUser);
            UserDto userDto = new UserDto();
            userDto.setEmail(updatedUser.getUsername());
            userDto.setFullname(updatedUser.getFullName());
            userDto.setSdt(updatedUser.getSdt());
            userDto.setUserId(updatedUser.getUserId());
            userDto.setUserId(updatedUser.getUserId());
            return userDto;
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    private String uploadToCloudinary(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("resource_type", "auto"));

        return uploadResult.get("secure_url").toString(); // link ảnh trực tiếp
    }
}