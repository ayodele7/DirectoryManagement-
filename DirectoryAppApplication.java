package com.example.DirectoryApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.DirectoryApp")
public class DirectoryAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DirectoryAppApplication.class, args);
	}

}
