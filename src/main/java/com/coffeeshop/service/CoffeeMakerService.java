package com.coffeeshop.service;

import com.coffeeshop.domain.Coffee;
import com.coffeeshop.repository.CoffeeMakerRepository;
import com.coffeeshop.utilitary.Ingredients;

import java.util.Map;

public class CoffeeMakerService implements ICoffeeMakerService {

    private CoffeeMakerRepository brewingRepository;

    public CoffeeMakerService(){}

    @Override
    public Coffee brewCoffeeAfterRecipe(Map<Ingredients, Ingredients> ingredients){
        return null;
    }
}
