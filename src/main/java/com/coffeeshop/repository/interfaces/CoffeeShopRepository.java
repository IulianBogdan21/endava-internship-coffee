package com.coffeeshop.repository.interfaces;

import java.util.List;

public interface CoffeeShopRepository<E> {
    List<E> getAll() throws Exception;

    void save(E entity) throws Exception;

    void update(E entity) throws Exception;
}
