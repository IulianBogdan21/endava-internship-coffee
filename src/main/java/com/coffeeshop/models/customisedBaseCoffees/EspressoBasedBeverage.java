package com.coffeeshop.models.customisedBaseCoffees;

import com.coffeeshop.models.coffeeRoot.CoffeeBase;
import com.coffeeshop.models.shop.Ingredients;

/**
 * class for a custom coffee that has espresso shots as base
 */
public class EspressoBasedBeverage extends CoffeeBase {

    public EspressoBasedBeverage(){}

    public EspressoBasedBeverage(Integer shotsOfBlackCoffee) {
        ingredientsForCoffeeAndAmount.put(Ingredients.BLACK_COFFEE, shotsOfBlackCoffee);
    }

    public EspressoBasedBeverage(String customerName, Integer shotsOfBlackCoffee) {
        ingredientsForCoffeeAndAmount.put(Ingredients.BLACK_COFFEE, shotsOfBlackCoffee);
        this.customerName = customerName;
    }

    public String getCoffeeName() {
        return this.customerName;
    }
}
