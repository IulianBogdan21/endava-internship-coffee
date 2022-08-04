package com.coffeeshop.repository;

import com.coffeeshop.domain.Pay;

import java.util.List;

public interface IPayRepository {
    List<Pay> getAll();

    Pay save(Pay payToSave);
}
