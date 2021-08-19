package com.example.filmeflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class FilmeflixApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmeflixApplication.class, args);
	}

}
