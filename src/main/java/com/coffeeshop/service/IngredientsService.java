package com.coffeeshop.service;

import com.coffeeshop.repository.IngredientsRepository;
import com.coffeeshop.utilitary.Ingredients;

import java.util.Map;

public class IngredientsService implements IIngredientsService {

    private IngredientsRepository ingredientsRepository;

    public IngredientsService(){}

    public IngredientsService(IngredientsRepository ingredientsRepository){
        this.ingredientsRepository = ingredientsRepository;
    }

    @Override
    public Map<Ingredients, Integer> getAllIngredients(){
        return null;
    }

    @Override
    public void updateStock(Map<Ingredients, Integer> consumedIngredients){

    }
}
