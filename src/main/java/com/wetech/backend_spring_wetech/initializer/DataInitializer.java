package com.wetech.backend_spring_wetech.initializer;

import com.wetech.backend_spring_wetech.repository.UserRepository;
import com.wetech.backend_spring_wetech.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

	@Value("${app.admin.email:admin@gmail.com}")
	private String adminEmail;

	@Value("${app.admin.password:admin}")
	private String adminPassword;

	@Value("${app.admin.fullname:Quản trị viên}")
	private String adminFullName;

	@Bean
	CommandLineRunner initAdmin(UserRepository userRepository, UserService userService) {
		return args -> {
			boolean hasAdminRole = userRepository.existsByRole("ADMIN");
			if (hasAdminRole) {
				return;
			}

			userService.createUserWithRole(adminEmail, adminPassword, adminFullName, "", "ADMIN");
			System.out.println("Created default admin: " + adminEmail);
		};
	}
}
