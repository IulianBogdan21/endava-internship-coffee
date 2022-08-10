package com.coffeeshop.service.interfaces;

import com.coffeeshop.models.shop.Ingredients;

import java.util.Map;

public interface IIngredientsService {
    Map<Ingredients, Integer> getAllIngredients() throws Exception;

    void updateStock(Map<Ingredients, Integer> consumedIngredients) throws Exception;

    boolean areSuppliesLow(Map<Ingredients, Integer> stock);
}
