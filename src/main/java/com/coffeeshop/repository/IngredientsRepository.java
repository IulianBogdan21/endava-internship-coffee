package com.coffeeshop.repository;

import com.coffeeshop.utilitary.Ingredients;
import com.coffeeshop.utilitary.StockManager;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

@Repository("ingredientsRepository")
public class IngredientsRepository implements IIngredientsRepository {

    private Map<Ingredients, Integer> stockOfIngredients;

    public IngredientsRepository(){}

    @PostConstruct
    public void initializeStock(){
        this.stockOfIngredients = StockManager.buildInitialStocks();
    }

    @Override
    public Map<Ingredients, Integer> getAll(){
        return stockOfIngredients;
    }

    /**
     * method updates the current stock of ingredients after preparing a coffee
     * @param consumedIngredients - ingredients consumed after making a certain coffee
     */
    @Override
    public void updateIngredients(Map<Ingredients, Integer> consumedIngredients){
        for(Ingredients iteratedConsumedIngredient: consumedIngredients.keySet()){
            int updatedQuantityForIngredient = stockOfIngredients.get(iteratedConsumedIngredient) -
                    consumedIngredients.get(iteratedConsumedIngredient);
            stockOfIngredients.put(iteratedConsumedIngredient, updatedQuantityForIngredient);
        }
    }
}
