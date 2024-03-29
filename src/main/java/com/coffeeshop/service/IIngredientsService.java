package com.coffeeshop.service;

import com.coffeeshop.utilitary.Ingredients;

import java.util.Map;

public interface IIngredientsService {
    Map<Ingredients, Integer> getAllIngredients();

    void updateStock(Map<Ingredients, Integer> consumedIngredients);

    boolean areSuppliesLow(Map<Ingredients, Integer> stock);
}
