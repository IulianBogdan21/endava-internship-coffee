package domain;

import utilitary.Ingredients;

public class CoffeeLatte extends Espresso {

    public CoffeeLatte() {
    }

    public CoffeeLatte(String customerName) {
        super(customerName);
        ingredientsForCoffeeAndAmount.put(Ingredients.STEAMED_MILK, 2);
        ingredientsForCoffeeAndAmount.put(Ingredients.MILK_FOAM, 1);
        this.customerName = customerName;
    }

    public String getCoffeeName() {
        return "domain.Coffee Latte";
    }
}
