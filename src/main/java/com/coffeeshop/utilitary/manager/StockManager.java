package com.coffeeshop.utilitary.manager;

import com.coffeeshop.models.shop.Ingredients;

import java.util.HashMap;
import java.util.Map;

/**
 * class used to initially build the stock of supplies (at the beginning of the day)
 */
public class StockManager {

    public static Map<Ingredients, Integer> buildInitialStocks(){
        Map<Ingredients, Integer> stocksPerDay = new HashMap<>();
        stocksPerDay.put(Ingredients.ESPRESSO, 20);
        stocksPerDay.put(Ingredients.BLACK_COFFEE, 20);
        stocksPerDay.put(Ingredients.CINNAMON, 10);
        stocksPerDay.put(Ingredients.HONEY, 10);
        stocksPerDay.put(Ingredients.MILK_FOAM, 15);
        stocksPerDay.put(Ingredients.STEAMED_MILK, 15);
        stocksPerDay.put(Ingredients.SYRUP, 10);
        return stocksPerDay;
    }

    /**
     * @param oneCoffeeIngredients - map that contains ingredients necessary for one coffee
     * @param numberOfCoffees - number of coffees of a kind
     * @return - total ingredients for an ordered amount of coffee
     */
    public static Map<Ingredients, Integer> evaluateAllIngredientsPerCoffeeCommand(Map<Ingredients, Integer> oneCoffeeIngredients,
                                                                                   Integer numberOfCoffees){
        Map<Ingredients, Integer> allIngredientsPerCoffeeCommand = new HashMap<>();
        for(Ingredients ingredient: oneCoffeeIngredients.keySet()){
            int amountOfIngredient = oneCoffeeIngredients.get(ingredient);
            allIngredientsPerCoffeeCommand.put(ingredient, amountOfIngredient * numberOfCoffees);
        }
        return allIngredientsPerCoffeeCommand;
    }
}
