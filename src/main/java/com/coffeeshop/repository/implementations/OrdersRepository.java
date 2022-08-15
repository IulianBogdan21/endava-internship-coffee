package com.coffeeshop.repository.implementations;

import com.coffeeshop.dao.implementations.OrdersDao;
import com.coffeeshop.models.dtos.OrderDto;
import com.coffeeshop.repository.interfaces.CoffeeShopRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ordersRepository")
public class OrdersRepository implements CoffeeShopRepository<OrderDto> {

    private final OrdersDao ordersDao;

    public OrdersRepository(OrdersDao ordersDao) {
        this.ordersDao = ordersDao;
    }

    @Override
    public List<OrderDto> getAll() throws Exception {
        return ordersDao.getAll();
    }

    @Override
    public void save(OrderDto entity) throws Exception {
        ordersDao.save(entity);
    }

    @Override
    public void update(OrderDto entity) throws Exception {}
}
