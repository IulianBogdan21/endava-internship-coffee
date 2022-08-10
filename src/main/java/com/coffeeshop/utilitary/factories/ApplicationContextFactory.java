package com.coffeeshop.utilitary.factories;

import com.coffeeshop.configuration.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * singleton factory for providing a unique instance of an ApplicationContext
 */
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
