package com.Elrearning;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Elrearning.models.User;
import com.Elrearning.models.Role;
import com.Elrearning.repository.RoleRepository;
import com.Elrearning.repository.UserRepository;

@SpringBootApplication
public class ElearningBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(ElearningBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode){
		return args ->{
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			User admin = new User(1, "admin", passwordEncode.encode("password"),"admin", roles);

			userRepository.save(admin);
		};
	}
}
