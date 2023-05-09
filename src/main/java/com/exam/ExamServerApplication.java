package com.exam;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamServerApplication implements CommandLineRunner {

	@Autowired
	private UserService service;
	public static void main(String[] args) {
		SpringApplication.run(ExamServerApplication.class, args);
	}
//
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("started");

//		User user = new User();
//		user.setFirstName("Javeed");
//		user.setLastName("Sharif");
//		user.setUsername("javeed798");
//		user.setPassword(this.passwordEncoder.encode("abc"));
//		user.setEmail("karishmavahida@gmail.com");
//		user.setPhone("987984899");
//		user.setProfile("1.png");
//
//		Role role = new Role();
//		role.setRoleId(44L);
//		role.setRoleName("ADMIN");
//
//		Set<UserRole> userRoleSet = new HashSet<>();
//		UserRole userRole = new UserRole();
//		userRole.setRole(role);
//		userRole.setUser(user);
//		userRoleSet.add(userRole);
//
//		User user1 = this.service.createUser(user, userRoleSet);
//		System.out.println(user1.getUsername());
	}
}
