package com.coffeeshop.rest.startRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.coffeeshop"})
@SpringBootApplication
public class StartRest {
    public static void main(String[] args) {
        SpringApplication.run(StartRest.class, args);
    }
}
