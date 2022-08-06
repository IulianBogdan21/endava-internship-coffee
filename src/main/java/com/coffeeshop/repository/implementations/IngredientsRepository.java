package com.coffeeshop.repository.implementations;

import com.coffeeshop.repository.interfaces.IIngredientsRepository;
import com.coffeeshop.models.shop.Ingredients;
import com.coffeeshop.utilitary.managers.StockManager;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

@Repository("ingredientsRepository")
public class IngredientsRepository implements IIngredientsRepository {

    private final Map<Ingredients, Integer> stockOfIngredients;

    public IngredientsRepository(){
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
