package com.santosh.ms.food.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsFoodProductApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsFoodProductApplication.class, args);
	}
}
