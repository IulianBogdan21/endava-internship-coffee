package com.coffeeshop.repository.implementations;

import com.coffeeshop.models.coffeeRoot.Coffee;
import com.coffeeshop.repository.interfaces.ICoffeeMakerRepository;
import com.coffeeshop.utilitary.CoffeeManager;
import com.coffeeshop.utilitary.Ingredients;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("coffeeMakerRepository")
public class CoffeeMakerRepository implements ICoffeeMakerRepository {

    @Override
    public Coffee brewCoffee(Map<Ingredients, Integer> coffeeIngredients){
        return CoffeeManager.makeCoffeeFromIngredients(coffeeIngredients);
    }
}