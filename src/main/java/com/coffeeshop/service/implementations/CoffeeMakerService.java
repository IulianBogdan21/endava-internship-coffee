package com.coffeeshop.service.implementations;

import com.coffeeshop.models.coffeeRoot.Coffee;
import com.coffeeshop.models.coffeeRoot.CoffeeBase;
import com.coffeeshop.models.customisedBaseCoffees.BlackCoffeeBasedBeverage;
import com.coffeeshop.models.customisedBaseCoffees.EspressoBasedBeverage;
import com.coffeeshop.service.interfaces.ICoffeeMakerService;
import com.coffeeshop.models.shop.Ingredients;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("coffeeMakerService")
public class CoffeeMakerService implements ICoffeeMakerService {

    public CoffeeMakerService(){}

    @Override
    public Coffee brewCoffeeAfterRecipe(Map<Ingredients, Integer> ingredients){
        Integer amountOfEspresso = ingredients.get(Ingredients.ESPRESSO);
        Integer amountOfBlackCoffee = ingredients.get(Ingredients.BLACK_COFFEE);
        CoffeeBase newCoffee;
        if(amountOfEspresso == null){
            newCoffee = new BlackCoffeeBasedBeverage(amountOfBlackCoffee);
            ingredients.remove(Ingredients.BLACK_COFFEE);
        }
        else{
            newCoffee = new EspressoBasedBeverage(amountOfEspresso);
            ingredients.remove(Ingredients.ESPRESSO);
        }
        for(Ingredients currentIngredient: ingredients.keySet()){
            newCoffee.addIngredient(currentIngredient, ingredients.get(currentIngredient));
        }
        return newCoffee;
    }
}
