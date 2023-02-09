package com.cleancode.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import({ApplicationConfiguration.class})
@SpringBootApplication
@ComponentScan(basePackages = {"com.cleancode.adapter", "com.cleancode.application"})
public class AdapterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdapterApplication.class, args);
    }

}
