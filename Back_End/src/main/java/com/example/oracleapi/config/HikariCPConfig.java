package com.example.oracleapi.config;
import com.zaxxer.hikari.HikariConfig; // Importando a classe correta de HikariCP
import com.zaxxer.hikari.HikariDataSource; // Para usar o HikariDataSource
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;



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

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.example.oracleapi.model");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.OraclecDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        em.setJpaProperties(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }


}

