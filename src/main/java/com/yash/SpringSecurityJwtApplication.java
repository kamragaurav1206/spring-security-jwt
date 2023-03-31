package com.yash;

import com.yash.entity.User;
import com.yash.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringSecurityJwtApplication {

	@Autowired
	private UserRepository userRepository;
	@PostConstruct
	public void initUser(){
		List<User> users = new ArrayList<>();
		User user = new User();
		users.add(new User(101,"Gaurav","password","gk@gmail.com"));
		users.add(new User(102,"Aadi","password","ak@gmail.com"));
		users.add(new User(103,"Sourabh","password","sk@gmail.com"));
		users.add(new User(104,"Monu","password","mk@gmail.com"));
		userRepository.saveAll(users);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApplication.class, args);
	}

}
