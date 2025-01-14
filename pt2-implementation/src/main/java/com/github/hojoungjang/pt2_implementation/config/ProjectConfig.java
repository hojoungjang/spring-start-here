package com.github.hojoungjang.pt2_implementation.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableFeignClients(
    basePackages = "com.github.hojoungjang.pt2_implementation.proxy"
)
public class ProjectConfig {
    // @Value("${custom.datasource.url}")
    // private String datasourceUrl;

    // @Value("${custom.datasource.username}")
    // private String datasourceUsername;

    // @Value("${custom.datasource.password}")
    // private String datasourcePassword;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // @Bean
    // public DataSource datasource() {
    //     HikariDataSource dataSource = new HikariDataSource();

    //     dataSource.setJdbcUrl(datasourceUrl);
    //     dataSource.setUsername(datasourceUsername);
    //     dataSource.setPassword(datasourcePassword);
    //     dataSource.setConnectionTimeout(1000);
    //     return dataSource;
    // }

}
