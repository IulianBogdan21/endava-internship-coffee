package com.coffeeshop.repository;

import com.coffeeshop.utilitary.Ingredients;

import java.util.Map;

public interface IIngredientsRepository {
    Map<Ingredients, Integer> getAll();

    void updateIngredients(Map<Ingredients, Integer> consumedIngredients);
}
