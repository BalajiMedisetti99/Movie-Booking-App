package com.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MovieServiceMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieServiceMsApplication.class, args);
	}

}
