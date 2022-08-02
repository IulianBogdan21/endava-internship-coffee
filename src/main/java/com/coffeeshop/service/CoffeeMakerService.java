package com.coffeeshop.service;

import com.coffeeshop.domain.Coffee;
import com.coffeeshop.repository.CoffeeMakerRepository;
import com.coffeeshop.utilitary.Ingredients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("coffeeMakerService")
public class CoffeeMakerService implements ICoffeeMakerService {

    private CoffeeMakerRepository coffeeMakerRepository;

    public CoffeeMakerService(){}

    @Autowired
    public CoffeeMakerService(CoffeeMakerRepository coffeeMakerRepository){
        this.coffeeMakerRepository = coffeeMakerRepository;
    }

    @Override
    public Coffee brewCoffeeAfterRecipe(Map<Ingredients, Integer> ingredients){
        return coffeeMakerRepository.brewCoffee(ingredients);
    }
}
