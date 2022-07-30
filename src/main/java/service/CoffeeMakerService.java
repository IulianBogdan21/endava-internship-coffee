package service;

import domain.Coffee;
import repository.CoffeeMakerRepository;
import utilitary.Ingredients;

import java.util.Map;

public class CoffeeMakerService implements ICoffeeMakerService {

    private CoffeeMakerRepository brewingRepository;

    public CoffeeMakerService(){}

    @Override
    public Coffee brewCoffeeAfterRecipe(Map<Ingredients, Ingredients> ingredients){
        return null;
    }
}
