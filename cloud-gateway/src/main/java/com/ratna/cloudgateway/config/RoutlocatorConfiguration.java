package com.ratna.cloudgateway.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.cloud.gateway.filter.factory.SpringCloudCircuitBreakerFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.DispatcherHandler;

import reactor.core.publisher.Mono;

@Configuration
public class RoutlocatorConfiguration extends SpringCloudCircuitBreakerFilterFactory {
	public RoutlocatorConfiguration(ReactiveCircuitBreakerFactory<?, ?> reactiveCircuitBreakerFactory,
			ObjectProvider<DispatcherHandler> dispatcherHandlerProvider) {
		super(reactiveCircuitBreakerFactory, dispatcherHandlerProvider);
	}

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

	@Override
	protected Mono<Void> handleErrorWithoutFallback(Throwable t, boolean resumeWithoutError) {
		// TODO Auto-generated method stub
		return null;
	}

}
