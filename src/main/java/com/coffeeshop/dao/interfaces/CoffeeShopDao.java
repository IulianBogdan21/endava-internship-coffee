package com.coffeeshop.dao.interfaces;

import java.util.List;

public interface CoffeeShopDao<E> {
    List<E> getAll() throws Exception;

    void update(E entity) throws Exception;

    void save(E entity);
}
