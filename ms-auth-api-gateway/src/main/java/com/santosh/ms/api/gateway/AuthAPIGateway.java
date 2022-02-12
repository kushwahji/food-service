package com.santosh.ms.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AuthAPIGateway {
	public static void main(String[] args) {
		SpringApplication.run(AuthAPIGateway.class, args);
	}
}
