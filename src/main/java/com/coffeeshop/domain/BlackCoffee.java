package com.coffeeshop.domain;

import com.coffeeshop.utilitary.Ingredients;

public class BlackCoffee extends Coffee {

    public BlackCoffee() {
    }

    public BlackCoffee(String customerName) {
        ingredientsForCoffeeAndAmount.put(Ingredients.BLACK_COFFEE, 1);
        this.customerName = customerName;
    }

    public String getCoffeeName() {
        return "Black coffee";
    }
}
