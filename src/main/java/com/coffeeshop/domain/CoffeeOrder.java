package com.coffeeshop.domain;

import com.coffeeshop.utilitary.Ingredients;
import com.coffeeshop.utilitary.OrderStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * order made by a customer
 * the order has a status (pickup or delivery) and a map where every ordered coffee also has a quantity(ex: 1,2 ...
 * coffees of type A)
 */
public class CoffeeOrder {
    private OrderStatus orderStatus;
    private Map<Coffee, Integer> orderedCoffeesAndQuantity;
    private String customerName;

    public CoffeeOrder() {
        orderedCoffeesAndQuantity = new HashMap<Coffee, Integer>();
    }

    public CoffeeOrder(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        orderedCoffeesAndQuantity = new HashMap<Coffee, Integer>();
    }

    public CoffeeOrder(OrderStatus orderStatus, String customerName) {
        this.orderStatus = orderStatus;
        orderedCoffeesAndQuantity = new HashMap<Coffee, Integer>();
        this.customerName = customerName;
    }

    public CoffeeOrder(String customerName, Map<Coffee, Integer> orderedCoffees){
        this.customerName =customerName;
        this.orderedCoffeesAndQuantity = orderedCoffees;
    }

    public Map<Coffee, Integer> getOrderedCoffeesAndQuantity() {
        return orderedCoffeesAndQuantity;
    }

    public void addCoffeeToOrder(Coffee coffeeToAdd, Integer quantity) {
        orderedCoffeesAndQuantity.put(coffeeToAdd, quantity);
    }

    /**
     * @param ingredientsAndTheirPrices  = map with all the prices for every ingredient
     * @return double - the price of the entire order made by the customer
     */
    public double getPriceOfOrder(Map<Ingredients, Double> ingredientsAndTheirPrices) {
        double orderTotalPay = 0;
        for (Coffee coffee : orderedCoffeesAndQuantity.keySet()) {
            orderTotalPay += orderedCoffeesAndQuantity.get(coffee) * coffee.getPrice(ingredientsAndTheirPrices);
        }
        return orderTotalPay;
    }

    public String getCustomerName() {
        return customerName;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
}
