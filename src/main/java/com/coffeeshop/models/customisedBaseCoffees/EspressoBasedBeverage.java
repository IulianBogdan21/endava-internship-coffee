package com.coffeeshop.models.customisedBaseCoffees;

import com.coffeeshop.models.coffeeRoot.CoffeeBase;
import com.coffeeshop.models.shop.Ingredients;

/**
 * class for a custom coffee that has espresso shots as base
 */
public class EspressoBasedBeverage extends CoffeeBase {

    public EspressoBasedBeverage(){}

    public EspressoBasedBeverage(Integer shotsOfEspresso) {
        recipe.put(Ingredients.ESPRESSO, shotsOfEspresso);
        setCoffeeName(customerName);
    }

    public EspressoBasedBeverage(String customerName, Integer shotsOfEspresso) {
        recipe.put(Ingredients.ESPRESSO, shotsOfEspresso);
        this.customerName = customerName;
        setCoffeeName(customerName);
    }

    public String getCoffeeName() {
        return coffeeName;
    }
}
