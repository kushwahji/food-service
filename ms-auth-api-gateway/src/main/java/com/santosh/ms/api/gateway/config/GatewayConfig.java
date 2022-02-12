package com.santosh.ms.api.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.santosh.ms.api.gateway.filter.JwtAuthenticationFilter;

/**
 * @author santosh.kushwah
 * @since 12-02-2022
 */
@Configuration
public class GatewayConfig {

	@Autowired
	private JwtAuthenticationFilter filter;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("auth", r -> r.path("/auth/**").
						filters(f -> f.filter(filter)).uri("lb://MS-AUTH-SERVICE"))

				.route("ms-product", r -> r.path("/product/**").
						filters(f -> f.filter(filter)).uri("lb://MS-PRODUCT"))
				
				.route("ms-order", r -> r.path("/order/**").
						filters(f -> f.filter(filter)).uri("lb://lb://MS-ORDER"))
				
				.route("ms-account", r -> r.path("/account/**").
						filters(f -> f.filter(filter)).uri("lb://lb://MS-ACCOUNT"))
				
				.route("ms-fund", r -> r.path("/fund/**").
						filters(f -> f.filter(filter)).uri("lb://MS-FUNDTRANSFER"))
				
				.route("ms-customer", r -> r.path("/customer/**").
						filters(f -> f.filter(filter)).uri("lb://MS-CUSTOMER"))
				
				.route("ms-food-product", r -> r.path("/food/product/**").
						filters(f -> f.filter(filter)).uri("lb://MS-FOOD-PRODUCT"))
				
				.route("ms-cart", r -> r.path("/cart/**").
						filters(f -> f.filter(filter)).uri("lb://MS-CART-SERVICE"))
				
				.route("ms-food", r -> r.path("/food/**").
						filters(f -> f.filter(filter)).uri("lb://MS-FOOD-SERVICE"))
				.build();
	}

}
