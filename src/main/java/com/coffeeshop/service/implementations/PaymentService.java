package com.coffeeshop.service.implementations;

import com.coffeeshop.models.customer.Payment;
import com.coffeeshop.repository.implementations.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("paymentService")
public class PaymentService implements com.coffeeshop.service.interfaces.IPaymentService {
    private PaymentRepository paymentRepository;

    public PaymentService() {}

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> getAllPayments(){
        return paymentRepository.getAll();
    }

    @Override
    public void savePayment(Payment paymentToSave){
        paymentRepository.save(paymentToSave);
    }
}
