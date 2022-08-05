package com.coffeeshop.repository.interfaces;

import com.coffeeshop.models.shop.Ingredients;

import java.util.Map;

public interface IIngredientsRepository {
    Map<Ingredients, Integer> getAll();

    void updateIngredients(Map<Ingredients, Integer> consumedIngredients);
}
