package com.tuvarna.geo;

import net.sf.log4jdbc.sql.jdbcapi.DataSourceSpy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;

import javax.sql.DataSource;

@Configuration
public class AppConfig {
    @Autowired
    DataSourceProperties dataSourceProperties;

    // @Bean
    // @ConfigurationProperties(prefix = DataSourceProperties.Xa)
    // DataSource realDataSource() {
    // DataSource dataSource = DataSourceInitializer
    // .create(this.dataSourceProperties.getClassLoader())
    // .url(this.dataSourceProperties.getUrl())
    // .username(this.dataSourceProperties.getUsername())
    // .password(this.dataSourceProperties.getPassword())
    // .build();
    // return dataSource;
    // }

    // @Bean
    // @Primary
    // DataSource dataSource() {
    // return new DataSourceSpy(realDataSource());
    // }
}