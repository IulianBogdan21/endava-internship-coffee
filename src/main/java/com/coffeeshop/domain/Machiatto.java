package com.coffeeshop.domain;

import com.coffeeshop.utilitary.Ingredients;

public class Machiatto extends Espresso {

    public Machiatto() {
    }

    public Machiatto(String customerName) {
        super(customerName);
        ingredientsForCoffeeAndAmount.put(Ingredients.MILK_FOAM, 1);
        this.customerName = customerName;
    }

    public String getCoffeeName() {
        return "Machiatto";
    }
}
