package com.example.soba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.SecureRandom;
import java.util.Base64;

@SpringBootApplication
public class SobaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SobaApplication.class, args);
	}

}
