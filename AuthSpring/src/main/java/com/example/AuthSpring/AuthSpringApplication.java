package com.example.AuthSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AuthSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthSpringApplication.class, args);
	}
}
