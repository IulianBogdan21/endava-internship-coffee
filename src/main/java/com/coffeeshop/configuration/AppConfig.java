package com.coffeeshop.configuration;

import com.coffeeshop.utilitary.printers.Printer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import java.util.Scanner;

@Configuration
@EnableScheduling
@ComponentScan({"com.coffeeshop"})
public class AppConfig {

    @Bean(name = "scanner")
    public Scanner getScanner(){
        return new Scanner(System.in);
    }

    @Bean(name = "printer")
    public Printer getPrinter(){
        return new Printer();
    }

    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }
}
