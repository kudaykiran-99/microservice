package com.uday.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	//in routes of api gateway has mmultiple routes(microservice as the acc, loans, cards)
	//if we have add application name in request(url) or any custom url request then we have modify the rotues
//in path it actual (http://localhost:8072/ACCOUNTS/api/create)has the after port no is micro serce name but we add uday bank

	//filter is used divide the url after micro service name. request to micro service to micro service path  (/api/creat) is segment
	//uri is the load balance to point eureka micro servcie actual captial lettters name

	@Bean
	public RouteLocator eazyBankRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p -> p
						.path("/udaybank/accounts/**")
						.filters( f -> f.rewritePath("/udaybank/accounts/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://ACCOUNTS"))
				.route(p -> p
						.path("/udaybank/loans/**")
						.filters( f -> f.rewritePath("/udaybank/loans/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://LOANS"))
				.route(p -> p
						.path("/udaybank/cards/**")
						.filters( f -> f.rewritePath("/udaybank/cards/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://CARDS")).build();


	}

}
