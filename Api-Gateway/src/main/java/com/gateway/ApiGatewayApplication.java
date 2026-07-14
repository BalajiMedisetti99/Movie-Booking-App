package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	
	@Bean
	public RouteLocator route(RouteLocatorBuilder builder) {
	    return builder.routes()
	            .route("Customer-Service-MS", r -> r.path("/api/v1/customers/**").uri("http://localhost:2222"))
	            .route("Movie-Service-MS", r -> r.path("/api/v1/movies/**").uri("http://localhost:3333"))
	            .route("Booking-Service-MS", r -> r.path("/api/v1/bookings/**").uri("http://localhost:1111"))
	            .route("Theater-Service-MS", r -> r.path("/api/v1/theaters/**").uri("http://localhost:4444"))
	            .build();
	}

}
