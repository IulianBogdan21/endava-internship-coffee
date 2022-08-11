package com.coffeeshop.models.defaultCoffees;

import com.coffeeshop.models.shop.Ingredients;

import javax.persistence.Entity;

@Entity
public class Cappucino extends Espresso {

    public Cappucino() {
    }

    public Cappucino(String customerName) {
        super(customerName);
        recipe.put(Ingredients.STEAMED_MILK, 1);
        recipe.put(Ingredients.MILK_FOAM, 2);
        this.customerName = customerName;
        setCoffeeName("Cappucino");
    }

    public String getCoffeeName() {
        return coffeeName;
    }
}
