package com.coffeeshop.service.interfaces;

import com.coffeeshop.models.customer.Payment;

import java.util.List;

public interface IPaymentService {
    List<Payment> getAllPayments();

    void savePayment(Payment paymentToSave);
}