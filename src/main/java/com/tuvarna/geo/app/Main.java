package com.tuvarna.geo.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan("com.tuvarna.geo")
@EnableJpaRepositories(basePackages = "com.tuvarna.geo.repository")
@EntityScan(basePackages = "com.tuvarna.geo.entity")
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
