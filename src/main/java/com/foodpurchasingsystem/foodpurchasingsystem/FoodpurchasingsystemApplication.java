package com.foodpurchasingsystem.foodpurchasingsystem;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;

@SpringBootApplication
public class FoodpurchasingsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodpurchasingsystemApplication.class, args);
	}
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
