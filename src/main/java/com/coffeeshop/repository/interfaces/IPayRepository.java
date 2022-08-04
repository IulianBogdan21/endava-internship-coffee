package com.coffeeshop.repository.interfaces;

import com.coffeeshop.models.customer.Pay;

import java.util.List;

public interface IPayRepository {
    List<Pay> getAll();

    Pay save(Pay payToSave);
}
