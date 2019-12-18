package com.example.springStudy.model.memo.dao;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean; 
import org.mybatis.spring.annotation.MapperScan; 
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.jdbc.datasource.DataSourceTransactionManager; 
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
@MapperScan(basePackages="com.example.springStudy.model.memo.dao")
public class DataConfig {
	@Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.mariadb.jdbc.Driver.class);
        dataSource.setUsername("");
        dataSource.setUrl("");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        return sessionFactory;
    }
}
