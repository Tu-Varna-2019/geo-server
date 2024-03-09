package com.tuvarna.geo.config;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class GeoDBConfig {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String URL = System.getenv("POSTGRE_URL");
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = System.getenv("POSTGRE_USER");
    private static final String PASSWORD = System.getenv("POSTGRE_PASSWORD");

    public GeoDBConfig() {
        loadSqlFile("geo/src/sql/create/create_tables.sql");
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    public void loadSqlFile(String filePath) {
        Resource resource = resourceLoader.getResource("classpath:" + filePath);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String sqlScript = reader.lines().collect(Collectors.joining("\n"));
            jdbcTemplate.execute(sqlScript);
            System.out.println("SQL script executed successfully.");
        } catch (IOException e) {
            System.err.println("Error loading SQL file: " + e.getMessage());
        }
    }

}
