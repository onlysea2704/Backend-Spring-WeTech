package com.wetech.backend_spring_wetech.initializer;

import com.wetech.backend_spring_wetech.entity.User;
import com.wetech.backend_spring_wetech.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initData(UserRepository userRepository) {
        return args -> {
            userRepository.deleteAll();

            List<User> users = List.of(
                    createUser("admin", "admin", "Quản trị viên", "0987654321", "admin@example.com", "ADMIN"),
                    createUser("user1", "user1", "Nguyễn Văn A", "0911111111", "user1@example.com", "USER"),
                    createUser("user2", "user2", "Trần Thị B", "0922222222", "user2@example.com", "USER"),
                    createUser("user3", "user3", "Lê Văn C", "0933333333", "user3@example.com", "USER"),
                    createUser("user4", "user4", "Phạm Thị D", "0944444444", "user4@example.com", "USER"),
                    createUser("user5", "user5", "Hoàng Văn E", "0955555555", "user5@example.com", "USER"),
                    createUser("user6", "user6", "Đinh Thị F", "0966666666", "user6@example.com", "USER"),
                    createUser("user7", "user7", "Bùi Văn G", "0977777777", "user7@example.com", "USER"),
                    createUser("user8", "user8", "Ngô Thị H", "0988888888", "user8@example.com", "USER"),
                    createUser("user9", "user9", "Vũ Văn I", "0999999999", "user9@example.com", "USER")
            );

            userRepository.saveAll(users);
        };
    }

    private User createUser(String username, String password, String fullname,
                            String sdt, String email, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setFullname(fullname);
        user.setSdt(sdt);
        user.setEmail(email);
        user.setRole(role);
        user.setCreated(new Date());
        return user;
    }
}
