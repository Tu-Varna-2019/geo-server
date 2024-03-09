package com.tuvarna.geo.config.app;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.tuvarna.geo.repository")
@EntityScan(basePackages = "com.tuvarna.geo.model")
public class PersistanceApp {

}
