package domain;

import utilitary.Ingredients;

/**
 * class for a custom coffee that has espresso shots as base
 */
public class EspressoBasedBeverage extends Coffee{

    public EspressoBasedBeverage(){}

    public EspressoBasedBeverage(String customerName, Integer shotsOfBlackCoffee) {
        ingredientsForCoffeeAndAmount.put(Ingredients.BLACK_COFFEE, shotsOfBlackCoffee);
        this.customerName = customerName;
    }

    public String getCoffeeName() {
        return this.customerName;
    }

    public void addIngredientToCoffee(Ingredients ingredientToAdd, Integer quantityOfIngredient){
        ingredientsForCoffeeAndAmount.put(ingredientToAdd, quantityOfIngredient);
    }
}
