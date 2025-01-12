package com.github.hojoungjang.pt2_implementation.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableFeignClients(
    basePackages = "com.github.hojoungjang.pt2_implementation.proxy"
)
public class ProjectConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
