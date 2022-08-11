package com.coffeeshop.models.defaultCoffees;

import com.coffeeshop.models.coffeeRoot.Coffee;
import com.coffeeshop.models.shop.Ingredients;

import javax.persistence.Entity;

@Entity
//@Table(name = "recipes")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Espresso extends Coffee {

    public Espresso() {}

    public Espresso(String customerName) {
        recipe.put(Ingredients.ESPRESSO, 1);
        this.customerName = customerName;
        setCoffeeName("Espresso");
    }

    public String getCoffeeName() {
        return coffeeName;
    }
}
