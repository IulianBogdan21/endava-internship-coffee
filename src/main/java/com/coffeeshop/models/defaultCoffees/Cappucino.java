package com.coffeeshop.models.defaultCoffees;

import com.coffeeshop.models.shop.Ingredients;

public class Cappucino extends Espresso {

    public Cappucino() {
    }

    public Cappucino(String customerName) {
        super(customerName);
        ingredientsForCoffeeAndAmount.put(Ingredients.STEAMED_MILK, 1);
        ingredientsForCoffeeAndAmount.put(Ingredients.MILK_FOAM, 2);
        this.customerName = customerName;
        setCoffeeName("Cappucino");
    }

    public String getCoffeeName() {
        return coffeeName;
    }
}
