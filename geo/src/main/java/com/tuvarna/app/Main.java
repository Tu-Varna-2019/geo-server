package com.tuvarna.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.tuvarna.geo")
@EnableJpaRepositories(basePackages = "com.tuvarna.geo.repository")
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
