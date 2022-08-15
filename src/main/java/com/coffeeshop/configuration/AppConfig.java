package com.coffeeshop.configuration;

import com.coffeeshop.rest.RestClient;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@Configuration
@EnableScheduling
@ComponentScan({"com.coffeeshop"})
public class AppConfig {

    @Bean(name = "scanner")
    public Scanner getScanner(){
        return new Scanner(System.in);
    }

    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }

    @Bean(name = "client")
    public RestClient getRestClient(){
        new RestTemplate();
        return new RestClient();
    }

    @Bean(name = "sessionFactory", value = "sessionFactory")
    public SessionFactory getSessionFactory(){
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try{
            return new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        catch (Exception exception){
            System.err.println("Exception: " + exception);
            StandardServiceRegistryBuilder.destroy(registry);
        }
        return null;
    }
}
