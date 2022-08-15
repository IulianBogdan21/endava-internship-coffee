package com.coffeeshop.repository.interfaces;

import com.coffeeshop.models.customer.Payment;

import java.util.List;

public interface IPaymentRepository {
    List<Payment> getAll();

    void save(Payment payToSave);
}
