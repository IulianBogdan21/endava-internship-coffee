package com.coffeeshop.repository.implementations;

import com.coffeeshop.models.customer.Payment;
import com.coffeeshop.repository.interfaces.CoffeeShopRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("paymentRepository")
public class PaymentRepository implements CoffeeShopRepository<Payment> {
    private final List<Payment> allPays;

    public PaymentRepository(){
        this.allPays = new ArrayList<>();
    }

    @Override
    public List<Payment> getAll(){
        return allPays;
    }

    @Override
    public void save(Payment paymentToSave){
        allPays.add(paymentToSave);
    }

    @Override
    public void update(Payment entity) throws Exception {}
}
