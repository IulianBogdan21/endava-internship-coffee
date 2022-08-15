package com.coffeeshop.service.interfaces;

import com.coffeeshop.models.coffeeRoot.Coffee;
import com.coffeeshop.models.shop.Ingredients;

import java.util.Map;

public interface ICoffeeMakerService {
    Coffee brewCoffeeAfterRecipe(Map<Ingredients, Integer> ingredients);
}