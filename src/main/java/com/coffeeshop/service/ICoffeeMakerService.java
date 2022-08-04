package com.coffeeshop.service;

import com.coffeeshop.models.coffeeRoot.Coffee;
import com.coffeeshop.utilitary.Ingredients;

import java.util.Map;

public interface ICoffeeMakerService {
    Coffee brewCoffeeAfterRecipe(Map<Ingredients, Integer> ingredients);
}
