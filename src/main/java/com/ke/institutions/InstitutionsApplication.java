package com.ke.institutions;

import com.ke.institutions.entity.Role;
import com.ke.institutions.entity.User;
import com.ke.institutions.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class InstitutionsApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;


	public static void main(String[] args) {
		SpringApplication.run(InstitutionsApplication.class, args);
	}

	public void run (String ... args) {
		User adminAccount = userRepository.findByRole(Role.ADMIN);

		if (null == adminAccount) {
			User user = new User();

			user.setEmail("admin@gmail.com");
			user.setFirstname("admin");
			user.setLastname("admin");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}


		User existingUser = userRepository.findByRole(Role.USER);

		// If no user with the role USER exists, create a new one
		if (existingUser == null) {
			User newUser = new User();
			newUser.setEmail("user@gmail.com");
			newUser.setFirstname("user");
			newUser.setLastname("user");
			newUser.setRole(Role.USER);
			newUser.setPassword(new BCryptPasswordEncoder().encode("user"));

			// Save the new user to the database
			userRepository.save(newUser);
		}
	}

}
