package com.coffeeshop.models.defaultCoffees;

import com.coffeeshop.models.shop.Ingredients;

public class CoffeeLatte extends Espresso {

    public CoffeeLatte() {}

    public CoffeeLatte(String customerName) {
        super(customerName);
        ingredientsForCoffeeAndAmount.put(Ingredients.STEAMED_MILK, 2);
        ingredientsForCoffeeAndAmount.put(Ingredients.MILK_FOAM, 1);
        this.customerName = customerName;
        setCoffeeName("CoffeeLatte");
    }

    public String getCoffeeName() {
        return coffeeName;
    }
}
