package com.sber.demo.sberdemoproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.sber.demo.sberdemoproject.*")
@ComponentScan(basePackages = { "com.sber.demo.sberdemoproject.*" })
@EntityScan("com.sber.demo.sberdemoproject.*")
@SpringBootApplication
public class SberDemoProjectApplication {
    static final Logger log =
            LoggerFactory.getLogger(SberDemoProjectApplication.class);

    public static void main(String[] args) {
        log.info("Starting up service...");
        SpringApplication.run(SberDemoProjectApplication.class, args);
        log.info("Service started with args " + args);
    }

}
