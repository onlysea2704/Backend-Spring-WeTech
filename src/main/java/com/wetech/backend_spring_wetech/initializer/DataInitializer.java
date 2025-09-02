package com.wetech.backend_spring_wetech.initializer;

import com.wetech.backend_spring_wetech.entity.User;
import com.wetech.backend_spring_wetech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initData(UserRepository userRepository) {
        return args -> {
            userRepository.deleteAll();

            // Tạo 1 số Course
            User userCustomer = new User();
            userCustomer.setUsername("user");
            userCustomer.setPassword(passwordEncoder.encode("user"));
            userCustomer.setRole("USER");
            userRepository.save(userCustomer);

            User userAdmin = new User();
            userAdmin.setUsername("admin");
            userAdmin.setPassword(passwordEncoder.encode("admin"));
            userAdmin.setRole("ADMIN");
            userRepository.save(userAdmin);
        };
    }
}
