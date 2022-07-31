package com.coffeeshop.service;

import com.coffeeshop.repository.IngredientsRepository;
import com.coffeeshop.utilitary.Ingredients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("ingredientsService")
public class IngredientsService implements IIngredientsService {

    private IngredientsRepository ingredientsRepository;

    public IngredientsService(){}

    @Autowired
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
