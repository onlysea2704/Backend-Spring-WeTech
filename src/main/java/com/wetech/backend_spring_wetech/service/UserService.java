package com.wetech.backend_spring_wetech.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.wetech.backend_spring_wetech.dto.*;
import com.wetech.backend_spring_wetech.entity.Course;
import com.wetech.backend_spring_wetech.entity.DeviceInfo;
import com.wetech.backend_spring_wetech.repository.DeviceInfoRepository;
import com.wetech.backend_spring_wetech.repository.UserRepository;
import com.wetech.backend_spring_wetech.utils.CloudinaryUtils;
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
import java.security.SecureRandom;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    CloudinaryUtils cloudinaryUtils;
    @Autowired
    DeviceInfoRepository deviceInfoRepository;
    @Autowired
    EmailService emailService;

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

    public boolean checkDevices(User user, DeviceInfoRequest deviceInfoRequest) {
        Long userId = user.getUserId();
        String userAgent = deviceInfoRequest.getUserAgent();
        String screen = deviceInfoRequest.getScreen();
        Integer cpuCores = deviceInfoRequest.getCpuCores();
        String ram = deviceInfoRequest.getRam();
        String gpu = deviceInfoRequest.getGpu();
        String platform = deviceInfoRequest.getPlatform();

        DeviceInfo deviceInfo = deviceInfoRepository.findDeviceInfoByUserIdAndUserAgentAndScreenAndCpuCoresAndRamAndGpuAndPlatform(userId, userAgent, screen, cpuCores, ram, gpu, platform);
        List<DeviceInfo> deviceInfoListByUser = deviceInfoRepository.findDeviceInfoByUserId(userId);

        if (deviceInfo != null || deviceInfoListByUser.size() <= 2) {
            if(deviceInfo == null) {
                DeviceInfo newDeviceInfo = new DeviceInfo();
                newDeviceInfo.setUserId(userId);
                newDeviceInfo.setUserAgent(userAgent);
                newDeviceInfo.setScreen(screen);
                newDeviceInfo.setCpuCores(cpuCores);
                newDeviceInfo.setRam(ram);
                newDeviceInfo.setGpu(gpu);
                newDeviceInfo.setPlatform(platform);
                deviceInfoRepository.save(newDeviceInfo);
            }
            return true;
        } else {
            return false;
        }
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

    public void changePassword(String username, ChangePasswordRequest request) {
        // 1. Tìm user trong DB
        User user = (User) loadUserByUsername(username); // Hoặc dùng userRepository.findByUsername(...)
        if (user == null) {
            throw new RuntimeException("Người dùng không tồn tại");
        }
        // 2. Kiểm tra mật khẩu cũ có khớp với mật khẩu đã mã hóa trong DB không
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Mật khẩu hiện tại không chính xác");
        }
        // 3. Mã hóa mật khẩu mới và lưu vào DB
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    public UserDto updateUser(UserUpdateRequest user, MultipartFile image) throws IOException {

        String imageUrl = user.getLinkImage();
        if (image != null) {
            if (imageUrl != null && !imageUrl.isEmpty()) {
                cloudinaryUtils.deleteFromCloudinary(imageUrl);
            }
            imageUrl = cloudinaryUtils.uploadToCloudinary(image);
        }
        Optional<User> optionalUser = userRepository.findByUsername(user.getEmail());
        if (optionalUser.isPresent()) {
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

    public boolean resetPasswordAndSendEmail(String email) {
        Optional<User> maybeUser = userRepository.findByUsername(email);
        if (maybeUser.isEmpty()) {
            return false;
        }

        User user = maybeUser.get();
        String newPassword = generateRandomPassword(10);
        String hashed = passwordEncoder.encode(newPassword);

        user.setPassword(hashed);
        userRepository.save(user);

        // Gửi email (chỉ text); tốt hơn là dùng template và kèm hướng dẫn đổi mật khẩu
        String subject = "Yêu cầu đặt lại mật khẩu";
        String text = String.format("Xin chào %s,\n\nMật khẩu mới của bạn là: %s\n\nVui lòng đăng nhập và đổi mật khẩu ngay.\n\nNếu bạn không yêu cầu, hãy liên hệ admin.",
                user.getFullName() == null ? user.getUsername() : user.getFullName(),
                newPassword);

        emailService.sendSimpleMail(user.getUsername(), subject, text);
        return true;
    }

    private String generateRandomPassword(int length) {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvwxyz"
                + "0123456789"
                + "!@#$%&*()-_+=<>?";

        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }
}