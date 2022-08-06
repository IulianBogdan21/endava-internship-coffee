package com.coffeeshop.service.implementations;

import com.coffeeshop.models.coffeeRoot.Coffee;
import com.coffeeshop.service.interfaces.ICoffeeMakerService;
import com.coffeeshop.models.shop.Ingredients;
import com.coffeeshop.utilitary.managers.CoffeeManager;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("coffeeMakerService")
public class CoffeeMakerService implements ICoffeeMakerService {

    public CoffeeMakerService(){}

    @Override
    public Coffee brewCoffeeAfterRecipe(Map<Ingredients, Integer> ingredients){
        return CoffeeManager.makeCoffeeFromIngredients(ingredients);
    }
}
