package com.santosh.ms.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsCardServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsCardServiceApplication.class, args);
	}
}
