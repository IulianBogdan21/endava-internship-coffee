package com.coffeeshop.utilitary.managers;

import com.coffeeshop.models.shop.Ingredients;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * class used to initially build the stock of supplies (at the beginning of the day)
 */
@Component("stockManager")
public class StockManager {

    public StockManager(){}

    /**
     * @param oneCoffeeIngredients - map that contains ingredients necessary for one coffee
     * @param numberOfCoffees - number of coffees of a kind
     * @return - total ingredients for an ordered amount of coffee
     */
    public Map<Ingredients, Integer> evaluateAllIngredientsPerCoffeeCommand(Map<Ingredients, Integer> oneCoffeeIngredients,
                                                                                   Integer numberOfCoffees){
        Map<Ingredients, Integer> allIngredientsPerCoffeeCommand = new HashMap<>();
        for(Ingredients ingredient: oneCoffeeIngredients.keySet()){
            int amountOfIngredient = oneCoffeeIngredients.get(ingredient);
            allIngredientsPerCoffeeCommand.put(ingredient, amountOfIngredient * numberOfCoffees);
        }
        return allIngredientsPerCoffeeCommand;
    }
}
