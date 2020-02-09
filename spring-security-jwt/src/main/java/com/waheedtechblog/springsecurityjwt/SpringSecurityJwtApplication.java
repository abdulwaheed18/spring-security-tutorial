package com.waheedtechblog.springsecurityjwt;

import com.waheedtechblog.springsecurityjwt.dao.UserRepository;
import com.waheedtechblog.springsecurityjwt.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringSecurityJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApplication.class, args);
	}

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Bean
	public CommandLineRunner demo(UserRepository userRepository) {
		return (args) -> {
			// Adding few user details in In-memory database
			// Password should be encrypted before saving to database
			userRepository.save(new User("Abdul", bcryptEncoder.encode("root123")));
			userRepository.save(new User("Captain", bcryptEncoder.encode("root123")));
			userRepository.save(new User("IronMan", bcryptEncoder.encode("pass")));
		};
	}

}
