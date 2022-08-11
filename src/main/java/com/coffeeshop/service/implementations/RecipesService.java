package com.coffeeshop.service.implementations;

import com.coffeeshop.models.coffeeRoot.Coffee;
import com.coffeeshop.repository.implementations.RecipesRepository;
import com.coffeeshop.service.interfaces.CoffeeShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("recipesService")
public class RecipesService implements CoffeeShopService {

    private final RecipesRepository recipesRepository;

    @Autowired
    public RecipesService(RecipesRepository recipesRepository){
        this.recipesRepository = recipesRepository;
    }

    public List<Coffee> getAllRecipes() throws Exception {
        return recipesRepository.getAll();
    }

    public void saveCoffee(Coffee coffeeToSave) throws Exception {
        recipesRepository.save(coffeeToSave);
    }
}
