package com.coffeeshop.repository.implementations;

import com.coffeeshop.models.customer.Pay;
import com.coffeeshop.repository.interfaces.IPayRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository("payRepository")
public class PayRepository implements IPayRepository {
    private List<Pay> allPays;

    public PayRepository(){}

    @PostConstruct
    public void initializePayRepository(){
        this.allPays = new ArrayList<>();
    }

    @Override
    public List<Pay> getAll(){
        return allPays;
    }

    @Override
    public Pay save(Pay payToSave){
        allPays.add(payToSave);
        return payToSave;
    }
}
