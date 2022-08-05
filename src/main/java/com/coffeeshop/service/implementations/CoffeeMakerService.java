package com.coffeeshop.service.implementations;

import com.coffeeshop.models.coffeeRoot.Coffee;
import com.coffeeshop.repository.implementations.CoffeeMakerRepository;
import com.coffeeshop.service.interfaces.ICoffeeMakerService;
import com.coffeeshop.models.shop.Ingredients;
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
