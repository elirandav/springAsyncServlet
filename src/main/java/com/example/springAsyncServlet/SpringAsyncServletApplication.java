package com.example.springAsyncServlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringAsyncServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAsyncServletApplication.class, args);
	}
}
