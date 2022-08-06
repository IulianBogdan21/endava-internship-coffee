package com.coffeeshop.repository.implementations;

import com.coffeeshop.models.customer.Payment;
import com.coffeeshop.repository.interfaces.IPaymentRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("payRepository")
public class PaymentRepository implements IPaymentRepository {
    private final List<Payment> allPays;

    public PaymentRepository(){
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
