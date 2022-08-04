package com.coffeeshop.rest.restService;

import com.coffeeshop.models.customer.Pay;
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

    public Pay create(Pay payToCreate){
        return execute(() -> restTemplate.postForObject(URL, payToCreate, Pay.class));
    }

    public Pay[] getPays(){
        return execute(() -> restTemplate.getForObject(URL, Pay[].class));
    }
}
