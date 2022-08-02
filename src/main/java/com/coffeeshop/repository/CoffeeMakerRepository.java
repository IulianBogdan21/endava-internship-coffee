package com.coffeeshop.repository;

import com.coffeeshop.domain.Coffee;
import com.coffeeshop.utilitary.CoffeeBuilder;
import com.coffeeshop.utilitary.Ingredients;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("coffeeMakerRepository")
public class CoffeeMakerRepository implements ICoffeeMakerRepository {

    @Override
    public Coffee brewCoffee(Map<Ingredients, Integer> coffeeIngredients){
        return CoffeeBuilder.makeCoffeeFromIngredients(coffeeIngredients);
    }
}
