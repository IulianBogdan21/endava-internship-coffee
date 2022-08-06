package com.coffeeshop.repository.implementations;

import com.coffeeshop.repository.interfaces.IIngredientsRepository;
import com.coffeeshop.models.shop.Ingredients;
import com.coffeeshop.utilitary.managers.StockManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("ingredientsRepository")
@DependsOn(value = "stockManager")
public class IngredientsRepository implements IIngredientsRepository {

    private final Map<Ingredients, Integer> stockOfIngredients;

    @Autowired
    public IngredientsRepository(StockManager stockManager){
        this.stockOfIngredients = stockManager.buildInitialStocks();
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
