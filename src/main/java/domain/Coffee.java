package domain;

import utilitary.Ingredients;

import java.util.HashMap;
import java.util.Map;

public abstract class Coffee {
    protected String customerName;
    protected Map<Ingredients, Integer> ingredientsForCoffeeAndAmount = new HashMap<>();

    public double getPrice(Map<Ingredients, Double> ingredientsPrices) {
        double finalPriceOfCoffee = 0;
        for (Ingredients ingredient : ingredientsForCoffeeAndAmount.keySet())
            finalPriceOfCoffee += ingredientsForCoffeeAndAmount.get(ingredient) * ingredientsPrices.get(ingredient);
        return finalPriceOfCoffee;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public abstract String getCoffeeName();
}
