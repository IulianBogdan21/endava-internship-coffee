package domain;

import utilitary.Ingredients;

/**
 * class for a custom coffee that has black coffee shots as base
 */
public class BlackCoffeeBasedBeverage extends Coffee{

    public BlackCoffeeBasedBeverage(){}

    public BlackCoffeeBasedBeverage(String customerName, Integer shotsOfBlackCoffee) {
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
