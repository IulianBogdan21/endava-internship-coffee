package com.coffeeshop.repository;

import com.coffeeshop.utilitary.Ingredients;
import com.coffeeshop.utilitary.StockBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("ingredientsRepository")
public class IngredientsRepository implements IIngredientsRepository {

    private Map<Ingredients, Integer> stockOfIngredients;

    public IngredientsRepository(){}

    @Autowired
    public void setStockOfIngredients(){
        this.stockOfIngredients = StockBuilder.buildInitialStocks();
    }

    @Override
    public Map<Ingredients, Integer> getAll(){
        return stockOfIngredients;
    }

    @Override
    public void updateIngredients(Map<Ingredients, Integer> consumedIngredients){

    }
}
