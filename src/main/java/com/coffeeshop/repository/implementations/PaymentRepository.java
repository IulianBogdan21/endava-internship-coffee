package com.coffeeshop.repository.implementations;

import com.coffeeshop.models.customer.Payment;
import com.coffeeshop.repository.interfaces.IPaymentRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository("payRepository")
public class PaymentRepository implements IPaymentRepository {
    private List<Payment> allPays;

    public PaymentRepository(){}

    @PostConstruct
    public void initializePayRepository(){
        this.allPays = new ArrayList<>();
    }

    @Override
    public List<Payment> getAll(){
        return allPays;
    }

    @Override
    public Payment save(Payment paymentToSave){
        allPays.add(paymentToSave);
        return paymentToSave;
    }
}
