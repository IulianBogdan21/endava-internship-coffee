package com.coffeeshop.models.coffeeRoot;

import com.coffeeshop.models.shop.Ingredients;

/**
 * class that extends the base Coffee class
 * it will be used as base class for all customisable coffees
 */
public abstract class CoffeeBase extends Coffee {

    /**
     * adding an ingredient to the coffee
     * @param ingredientToAdd Ingredients
     * @param amountOfIngredient integer
     */
    public void addIngredient(Ingredients ingredientToAdd, Integer amountOfIngredient){
        recipe.put(ingredientToAdd, amountOfIngredient);
    }
}
