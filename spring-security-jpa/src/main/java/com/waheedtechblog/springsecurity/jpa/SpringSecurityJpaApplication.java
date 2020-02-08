package com.waheedtechblog.springsecurity.jpa;

import com.waheedtechblog.springsecurity.jpa.dao.UserRepository;
import com.waheedtechblog.springsecurity.jpa.domain.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.waheedtechblog.springsecurity.jpa")
public class SpringSecurityJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository) {
        return (args) -> {
            // Adding few user details in In-memory database
			// Password should be encrypted before saving to database
            userRepository.save(new User("Abdul", "root123", "ROLE_ADMIN,ROLE_USER"));
            userRepository.save(new User("Captain", "root123", "ROLE_USER"));
            userRepository.save(new User("IronMan", "pass", null));
        };
    }

}
