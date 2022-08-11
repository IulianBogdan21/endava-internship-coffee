package com.coffeeshop.models.customisedBaseCoffees;

import com.coffeeshop.models.coffeeRoot.CoffeeBase;
import com.coffeeshop.models.shop.Ingredients;

/**
 * class for a custom coffee that has black coffee shots as base
 */
public class BlackCoffeeBasedBeverage extends CoffeeBase {

    public BlackCoffeeBasedBeverage(){}

    public BlackCoffeeBasedBeverage(Integer shotsOfBlackCoffee) {
        ingredientsForCoffeeAndAmount.put(Ingredients.BLACK_COFFEE, shotsOfBlackCoffee);
        this.coffeeName = customerName;
    }

    public BlackCoffeeBasedBeverage(String customerName, Integer shotsOfBlackCoffee) {
        ingredientsForCoffeeAndAmount.put(Ingredients.BLACK_COFFEE, shotsOfBlackCoffee);
        this.customerName = customerName;
        this.coffeeName = customerName;
    }

    public String getCoffeeName() {
        return this.customerName;
    }
}
