package com.coffeeshop.repository;

import com.coffeeshop.domain.Pay;

import java.util.List;

public interface IPayRepository {
    List<Pay> getAll();

    void save(Pay payToSave);
}
