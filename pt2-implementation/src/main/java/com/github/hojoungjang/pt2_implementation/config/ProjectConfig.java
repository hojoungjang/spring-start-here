package com.github.hojoungjang.pt2_implementation.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(
    basePackages = "com.github.hojoungjang.pt2_implementation.proxy"
)
public class ProjectConfig {
    
}
