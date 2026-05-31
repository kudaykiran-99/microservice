package com.uday.springconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


@SpringBootApplication
@EnableConfigServer
public class SpringconfigserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringconfigserverApplication.class, args);
	}

}
