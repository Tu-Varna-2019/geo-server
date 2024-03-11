package com.tuvarna.geo.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import jakarta.annotation.PostConstruct;

@Configuration
public class GeoDBConfig<T> {

    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Map<Class<?>, RowMapper<?>> rowMappers = new ConcurrentHashMap<>();

    private final Logger logger = LogManager.getLogger(GeoDBConfig.class);

    public GeoDBConfig() {

    }

    public <T> void registerRowMapper(Class<T> type, RowMapper<T> rowMapper) {
        rowMappers.put(type, rowMapper);
    }

    @SuppressWarnings("unchecked")
    public <T> RowMapper<T> getRowMapper(Class<T> type) {
        return (RowMapper<T>) rowMappers.get(type);
    }

    public <T> List<T> executeSql(String sql, RowMapper<T> rowMapper, Object... params) throws DataAccessException {

        if (rowMapper != null) {
            return executeQuery(sql, rowMapper, params);
        } else {

            executeUpdate(sql, params);

            return Collections.emptyList();
        }
    }

    private <T> List<T> executeQuery(String sql, RowMapper<T> rowMapper, Object... params) {

        return params == null || params.length == 0 ? jdbcTemplate.query(sql, rowMapper)
                : jdbcTemplate.query(sql, params, rowMapper);
    }

    private void executeUpdate(String sql, Object... params) {
        if (params == null || params.length == 0) {
            jdbcTemplate.update(sql);
        } else {
            jdbcTemplate.update(sql, params);
        }
    }

    public void loadSqlFile(String filePath, Map<String, Object> params) {
        try {
            Resource resource = resourceLoader.getResource("classpath:" + filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            StringBuilder sqlBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sqlBuilder.append(line).append("\n");
            }

            String sqlContent = sqlBuilder.toString();

            if (params != null) {
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    String paramName = entry.getKey();
                    Object paramValue = entry.getValue();
                    sqlContent = sqlContent.replace(":" + paramName, paramValue.toString());
                }
            }

            jdbcTemplate.execute(sqlContent);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
