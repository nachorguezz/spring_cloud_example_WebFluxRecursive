package com.ejercicio1.configService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ApplicationConfigService {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfigService.class, args);
    }
}
