package com.coffeeshop.repository.interfaces;

import com.coffeeshop.models.coffeeRoot.Coffee;
import com.coffeeshop.models.shop.Ingredients;

import java.util.Map;

public interface ICoffeeMakerRepository {
    Coffee brewCoffee(Map<Ingredients, Integer> coffeeIngredients);
}
