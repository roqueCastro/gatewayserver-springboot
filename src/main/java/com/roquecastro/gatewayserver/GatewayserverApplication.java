package com.roquecastro.gatewayserver;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}
	
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		// CONFIGURAR RUTA DE MANERA CUSTOMIZADA
		
		
		return builder.routes()
				.route(p -> p
						.path("/applications/apihotel/**")
						.filters(f -> f.rewritePath("/applications/apihotel/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", new Date().toString()))
						
						.uri("lb://HOTELS"))
				.route(p -> p
						.path("/applications/apirooms/**")
						.filters(f -> f.rewritePath("/applications/apirooms/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", new Date().toString()))
						
						.uri("lb://ROOMS"))
				.route(p -> p
						.path("/applications/apireservations/**")
						.filters(f -> f.rewritePath("/applications/apireservations/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", new Date().toString()))
						
						.uri("lb://RESERVATIONS")).build();
				
	}

}
