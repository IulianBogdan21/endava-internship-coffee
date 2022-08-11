package com.coffeeshop.models.defaultCoffees;

import com.coffeeshop.models.shop.Ingredients;

import javax.persistence.Entity;

@Entity
public class CoffeeMiel extends BlackCoffee {

    public CoffeeMiel() {
    }

    public CoffeeMiel(String customerName) {
        super(customerName);
        recipe.put(Ingredients.HONEY, 1);
        recipe.put(Ingredients.CINNAMON, 1);
        recipe.put(Ingredients.STEAMED_MILK, 1);
        this.customerName = customerName;
        setCoffeeName("Coffee Miel");
    }

    public String getCoffeeName() {
        return coffeeName;
    }
}
