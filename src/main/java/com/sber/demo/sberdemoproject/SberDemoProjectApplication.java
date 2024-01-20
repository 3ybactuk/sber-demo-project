package com.sber.demo.sberdemoproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableJpaRepositories("com.sber.demo.sberdemoproject.*")
@ComponentScan(basePackages = { "com.sber.demo.sberdemoproject.*" })
@EntityScan("com.sber.demo.sberdemoproject.*")
@EnableSwagger2
@EnableWebMvc
@SpringBootApplication
public class SberDemoProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SberDemoProjectApplication.class, args);
    }

}
