package com.coffeeshop.utilitary;

import java.util.HashMap;
import java.util.Map;

/**
 * class with a single static method that builds the prices for every ingredient and returns a dictionary
 */
public class PricesBuilder {
    /**
     * @return dictionary where key is an ingredient and value is the price for 1 unit of that ingredient
     */
    public static Map<Ingredients, Double> buildPricesForIngredients() {
        Map<Ingredients, Double> pricesForEachIngredient = new HashMap<>();
        pricesForEachIngredient.put(Ingredients.ESPRESSO, 2.5);
        pricesForEachIngredient.put(Ingredients.BLACK_COFFEE, 1.5);
        pricesForEachIngredient.put(Ingredients.CINNAMON, 2.0);
        pricesForEachIngredient.put(Ingredients.HONEY, 3.1);
        pricesForEachIngredient.put(Ingredients.MILK_FOAM, 3.5);
        pricesForEachIngredient.put(Ingredients.STEAMED_MILK, 3.6);
        pricesForEachIngredient.put(Ingredients.SYRUP, 4.0);
        return pricesForEachIngredient;
    }
}
