package com.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CustomerServiceMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceMsApplication.class, args);
	}

}
