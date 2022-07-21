package domain;

import utilitary.Ingredients;

public class CoffeeMiel extends BlackCoffee {

    public CoffeeMiel() {
    }

    public CoffeeMiel(String customerName) {
        super(customerName);
        ingredientsForCoffeeAndAmount.put(Ingredients.HONEY, 1);
        ingredientsForCoffeeAndAmount.put(Ingredients.CINNAMON, 1);
        ingredientsForCoffeeAndAmount.put(Ingredients.STEAMED_MILK, 1);
        this.customerName = customerName;
    }

    public String getCoffeeName() {
        return "Coffee Miel";
    }
}
