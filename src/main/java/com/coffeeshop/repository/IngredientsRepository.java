package com.coffeeshop.repository;

import com.coffeeshop.utilitary.Ingredients;

import java.util.Map;

public class IngredientsRepository implements IIngredientsRepository {

    private Map<Ingredients, Integer> stockOfIngredients;

    @Override
    public Map<Ingredients, Integer> getAll(){
        return null;
    }

    @Override
    public void updateIngredients(Map<Ingredients, Integer> consumedIngredients){

    }
}
