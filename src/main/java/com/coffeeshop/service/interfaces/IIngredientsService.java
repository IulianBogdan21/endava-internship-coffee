package com.coffeeshop.service.interfaces;

import com.coffeeshop.models.shop.Ingredients;

import java.util.Map;

public interface IIngredientsService {
    Map<Ingredients, Integer> getAllIngredients();

    void updateStock(Map<Ingredients, Integer> consumedIngredients);

    boolean areSuppliesLow(Map<Ingredients, Integer> stock);
}
