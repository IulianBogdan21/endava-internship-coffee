package service;

import domain.Coffee;
import utilitary.Ingredients;

import java.util.Map;

public interface ICoffeeMakerService {
    Coffee brewCoffeeAfterRecipe(Map<Ingredients, Ingredients> ingredients);
}
