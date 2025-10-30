package com.fq.config.jdbc;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataJdbcConfig {


    @Bean
    @Qualifier("mysql")
    @Primary
    @ConfigurationProperties(prefix = "datasource.mysql")
    public HikariDataSource mysqlDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    @Qualifier("mysql")
    @Primary
    public NamedParameterJdbcTemplate mysqlNamedJdbcTemplate(@Qualifier("mysql") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

//    @Bean
//    @Qualifier("hive")
//    @ConfigurationProperties(prefix = "datasource.hive")
//    public HikariDataSource hiveDataSource() {
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }

//    @Bean
//    @Qualifier("mysql")
//    @Primary
//    public JdbcTemplate jdbcTemplate(@Qualifier("mysql") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }

//    @Bean
//    @Qualifier("hive")

//    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Qualifier("hive") DataSource dataSource) {
//        return new NamedParameterJdbcTemplate(dataSource);
//    }

}
