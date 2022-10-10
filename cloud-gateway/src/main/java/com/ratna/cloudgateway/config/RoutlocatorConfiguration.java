package com.ratna.cloudgateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutlocatorConfiguration {
	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("order-service", r -> r.path("/order/**").filters(
						f -> f.circuitBreaker(c -> c.setName("order-service").setFallbackUri("forward:/orderFallBack")))
						.uri("lb://order-service"))
				.route("payment-service",
						r -> r.path("/payment/**")
								.filters(f -> f.circuitBreaker(
										c -> c.setName("payment-service").setFallbackUri("forward:/paymentFallBack")))
								.uri("lb://payment-service"))
				.build();
	}

}
