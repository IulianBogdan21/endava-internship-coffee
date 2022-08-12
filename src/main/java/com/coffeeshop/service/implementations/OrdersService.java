package com.coffeeshop.service.implementations;

import com.coffeeshop.models.dtos.OrderDto;
import com.coffeeshop.repository.implementations.OrdersRepository;
import com.coffeeshop.service.interfaces.CoffeeShopService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ordersService")
public class OrdersService implements CoffeeShopService{

    private final OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<OrderDto> getAllOrders() throws Exception {
        return ordersRepository.getAll();
    }

    public void saveOrder(OrderDto orderDto) throws Exception {
        ordersRepository.save(orderDto);
    }
}
