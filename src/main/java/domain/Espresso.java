package domain;

import utilitary.Ingredients;

public class Espresso extends Coffee {

    public Espresso() {
    }

    public Espresso(String customerName) {
        ingredientsForCoffeeAndAmount.put(Ingredients.ESPRESSO, 1);
        this.customerName = customerName;
    }

    public String getCoffeeName() {
        return "Espresso";
    }
}
