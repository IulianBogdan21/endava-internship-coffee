package com.coffeeshop.models.customer;

import com.coffeeshop.models.coffeeRoot.Coffee;

import java.util.HashMap;
import java.util.Map;

/**
 * order made by a customer
 * the order has a status (pickup or delivery) and a map where every ordered coffee also has a quantity(ex: 1,2 ...
 * coffees of type A)
 */
public class CoffeeOrder {
    private final OrderStatus orderStatus;
    private final Map<Coffee, Integer> orderedCoffeesAndQuantity;
    private String customerName;

    public CoffeeOrder(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        orderedCoffeesAndQuantity = new HashMap<>();
    }

    public Map<Coffee, Integer> getOrderedCoffeesAndQuantity() {
        return orderedCoffeesAndQuantity;
    }

    public void addCoffeeToOrder(Coffee coffeeToAdd, Integer quantity) {
        orderedCoffeesAndQuantity.put(coffeeToAdd, quantity);
    }

    /**
     * @return double - the price of the entire order made by the customer
     */
    public double getPriceOfOrder() {
        double orderTotalPay = 0;
        for (Coffee coffee : orderedCoffeesAndQuantity.keySet()) {
            orderTotalPay += orderedCoffeesAndQuantity.get(coffee) * coffee.getPrice();
        }
        return orderTotalPay;
    }

    public String getCustomerName() {
        return customerName;
    }

    public OrderStatus getOrderStatus(){
        return orderStatus;
    }

    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }
}
