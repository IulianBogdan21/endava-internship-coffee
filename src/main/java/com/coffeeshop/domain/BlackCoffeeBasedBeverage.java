package com.coffeeshop.domain;

import com.coffeeshop.utilitary.Ingredients;

/**
 * class for a custom coffee that has black coffee shots as base
 */
public class BlackCoffeeBasedBeverage extends CoffeeBase{

    public BlackCoffeeBasedBeverage(){}

    public BlackCoffeeBasedBeverage(Integer shotsOfBlackCoffee) {
        ingredientsForCoffeeAndAmount.put(Ingredients.BLACK_COFFEE, shotsOfBlackCoffee);
    }

    public BlackCoffeeBasedBeverage(String customerName, Integer shotsOfBlackCoffee) {
        ingredientsForCoffeeAndAmount.put(Ingredients.BLACK_COFFEE, shotsOfBlackCoffee);
        this.customerName = customerName;
    }

    public String getCoffeeName() {
        return this.customerName;
    }
}
