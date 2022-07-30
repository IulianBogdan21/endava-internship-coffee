package com.coffeeshop.domain;

import com.coffeeshop.utilitary.Ingredients;

public class Cappucino extends Espresso {

    public Cappucino() {
    }

    public Cappucino(String customerName) {
        super(customerName);
        ingredientsForCoffeeAndAmount.put(Ingredients.STEAMED_MILK, 1);
        ingredientsForCoffeeAndAmount.put(Ingredients.MILK_FOAM, 2);
        this.customerName = customerName;
    }

    public String getCoffeeName() {
        return "Cappucino";
    }
}
