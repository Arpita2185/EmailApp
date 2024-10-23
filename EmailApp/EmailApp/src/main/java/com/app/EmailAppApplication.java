package com.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailAppApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(EmailAppApplication.class, args);
		System.out.println("Welcome EmailAppApplication");
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
		
}
