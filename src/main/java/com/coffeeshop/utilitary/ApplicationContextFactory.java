package com.coffeeshop.utilitary;

import configuration.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextFactory {

    private static ApplicationContext instance;

    private ApplicationContextFactory(){}

    public static synchronized ApplicationContext getInstance(){
        if(instance == null){
            instance = new AnnotationConfigApplicationContext(AppConfig.class);
        }
        return instance;
    }
}
