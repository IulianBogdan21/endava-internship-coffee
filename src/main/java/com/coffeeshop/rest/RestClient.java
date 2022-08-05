package com.coffeeshop.rest;

import com.coffeeshop.models.customer.Payment;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

public class RestClient {
    public static final String URL = "http://localhost:8080/orders/pay";

    private final RestTemplate restTemplate = new RestTemplate();

    private <T> T execute(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Payment create(Payment paymentToCreate){
        return execute(() -> restTemplate.postForObject(URL, paymentToCreate, Payment.class));
    }

    public Payment[] getPays(){
        return execute(() -> restTemplate.getForObject(URL, Payment[].class));
    }
}
