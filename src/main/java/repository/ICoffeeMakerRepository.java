package repository;

import domain.Coffee;
import utilitary.Ingredients;

import java.util.Map;

public interface ICoffeeMakerRepository {
    Coffee getCoffeeWithGivenIngredients(Map<Ingredients, Integer> coffeeIngredients);
}
