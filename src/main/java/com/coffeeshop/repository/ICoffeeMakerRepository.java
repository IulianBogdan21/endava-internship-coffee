package com.coffeeshop.repository;

import com.coffeeshop.domain.Coffee;
import com.coffeeshop.utilitary.Ingredients;

import java.util.Map;

public interface ICoffeeMakerRepository {
    Coffee brewCoffee(Map<Ingredients, Integer> coffeeIngredients);
}
