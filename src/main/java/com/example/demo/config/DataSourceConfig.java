//package com.example.demo.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import javax.sql.DataSource;
//
///**
// * Configures the Spring-managed resources for Common Services/Utils.
// */
//@Configuration
//public class DataSourceConfig {
//
//    @Autowired
//    Environment env;
//
//    /**
//     * Primary DataSource (Meaning the one that is your parent transaction manager)
//     */
//    @Bean
//    @Primary
//    public DataSource h2DataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getProperty("spring.datasource-primary.driverClassName"));
//        dataSource.setUrl(env.getProperty("spring.datasource-primary.url"));
//        dataSource.setUsername(env.getProperty("spring.datasource-primary.username"));
//        dataSource.setPassword(env.getProperty("spring.datasource-primary.password"));
//        return dataSource;
//    }
//
//    /**
//     * @usage Autowire this in your JPA Repositories using
//     *      @Autowired
//     *      JdbcTemplate h2JdbcTemplate;
//     */
//    @Bean
//    public JdbcTemplate h2JdbcTemplate() {
//        return new JdbcTemplate(h2DataSource());
//    }
//
//    /**
//     * Secondary DataSource (Meaning the one that can cause the parent transaction to roll-back on exception)
//     */
//    @Bean
//    public DataSource mysqlDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getProperty("spring.datasource-secondary.driverClassName"));
//        dataSource.setUrl(env.getProperty("spring.datasource-secondary.url"));
//        dataSource.setUsername(env.getProperty("spring.datasource-primary.username"));
//        dataSource.setPassword(env.getProperty("spring.datasource-secondary.password"));
//        return dataSource;
//    }
//
//    /**
//     * @usage Autowire this in your JPA Repositories using
//     *      @Autowired
//     *      JdbcTemplate mysqlJdbcTemplate;
//     */
//    @Bean
//    public JdbcTemplate mysqlJdbcTemplate() {
//        return new JdbcTemplate(mysqlDataSource());
//    }
//}
