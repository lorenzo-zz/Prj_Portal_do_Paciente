package com.example.oracleapi.config;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource; 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import javax.sql.DataSource;



public class HikariCPConfig {
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();  // Usando o HikariConfig da biblioteca
        hikariConfig.setJdbcUrl(dbUrl);
        hikariConfig.setUsername(dbUsername);
        hikariConfig.setPassword(dbPassword);
        hikariConfig.setDriverClassName("oracle.jdbc.OracleDriver");

        // Configurações de pool
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(5);
        hikariConfig.setIdleTimeout(30000);
        hikariConfig.setConnectionTimeout(30000);
        hikariConfig.setMaxLifetime(1800000);
        hikariConfig.setPoolName("HikariPool-Oracle");

        return new HikariDataSource(hikariConfig);
    }
}

