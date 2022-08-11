package com.coffeeshop.models.defaultCoffees;

import com.coffeeshop.models.coffeeRoot.Coffee;
import com.coffeeshop.models.shop.Ingredients;

import javax.persistence.Entity;

@Entity
public class BlackCoffee extends Coffee {

    public BlackCoffee() {
    }

    public BlackCoffee(String customerName) {
        recipe.put(Ingredients.BLACK_COFFEE, 1);
        this.customerName = customerName;
        setCoffeeName("Black coffee");
    }

    public String getCoffeeName() {
        return coffeeName;
    }
}
