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
                    createUser("admin@gmail.com", "admin", "Quản trị viên", "0987654321", "admin@example.com", "ADMIN"),
                    createUser("user1@gmail.com", "user1", "Nguyễn Văn A", "0911111111", "user1@example.com", "USER"),
                    createUser("user2@gmail.com", "user2", "Trần Thị B", "0922222222", "user2@example.com", "USER"),
                    createUser("user3@gmail.com", "user3", "Lê Văn C", "0933333333", "user3@example.com", "USER"),
                    createUser("user4@gmail.com", "user4", "Phạm Thị D", "0944444444", "user4@example.com", "USER"),
                    createUser("user5@gmail.com", "user5", "Hoàng Văn E", "0955555555", "user5@example.com", "USER"),
                    createUser("user6@gmail.com", "user6", "Đinh Thị F", "0966666666", "user6@example.com", "USER"),
                    createUser("user7@gmail.com", "user7", "Bùi Văn G", "0977777777", "user7@example.com", "USER"),
                    createUser("user8@gmail.com", "user8", "Ngô Thị H", "0988888888", "user8@example.com", "USER"),
                    createUser("user9@gmail.com", "user9", "Vũ Văn I", "0999999999", "user9@example.com", "USER"),
                    createUser("user10@gmail.com", "user10", "Hoàng Văn E", "0955555555", "user5@example.com", "USER"),
                    createUser("user11@gmail.com", "user11", "Đinh Thị F", "0966666666", "user6@example.com", "USER"),
                    createUser("user12@gmail.com", "user12", "Bùi Văn G", "0977777777", "user7@example.com", "USER"),
                    createUser("user13@gmail.com", "user13", "Ngô Thị H", "0988888888", "user8@example.com", "USER"),
                    createUser("user14@gmail.com", "user14", "Vũ Văn I", "0999999999", "user9@example.com", "USER")
            );

            userRepository.saveAll(users);
        };
    }

    private User createUser(String username, String password, String fullName,
                            String sdt, String email, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setFullName(fullName);
        user.setSdt(sdt);
//        user.setEmail(email);
        user.setRole(role);
        user.setCreated(new Date());
        return user;
    }
}
