package com.coffeeshop.service.implementations;

import com.coffeeshop.repository.implementations.IngredientsRepository;
import com.coffeeshop.service.interfaces.IIngredientsService;
import com.coffeeshop.utilitary.Ingredients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("ingredientsService")
public class IngredientsService implements IIngredientsService {

    private IngredientsRepository ingredientsRepository;

    private static final int MINIMUM_BASE_COFFEE_STOCK = 9;
    private static final int MINIMUM_MILK_RELATED_STOCK = 6;
    private static final int MINIMUM_ADDITIONAL_INGREDIENTS_STOCK = 3;

    public IngredientsService(){}

    @Autowired
    public IngredientsService(IngredientsRepository ingredientsRepository){
        this.ingredientsRepository = ingredientsRepository;
    }

    @Override
    public Map<Ingredients, Integer> getAllIngredients(){
        return ingredientsRepository.getAll();
    }

    @Override
    public void updateStock(Map<Ingredients, Integer> consumedIngredients){
        ingredientsRepository.updateIngredients(consumedIngredients);
    }

    /**
     * @param stock - current stock of ingredients
     * @return true if the shop is low on some ingredients, false otherwise
     */
    public boolean areSuppliesLow(Map<Ingredients, Integer> stock){
        return notEnoughBaseIngredients(stock) || notEnoughMilkIngredients(stock) || notEnoughAdditionalIngredients(stock);
    }

    private boolean notEnoughBaseIngredients(Map<Ingredients, Integer> stock){
        return stock.get(Ingredients.ESPRESSO) <= MINIMUM_BASE_COFFEE_STOCK ||
                stock.get(Ingredients.BLACK_COFFEE) <= MINIMUM_BASE_COFFEE_STOCK;
    }

    private boolean notEnoughMilkIngredients(Map<Ingredients, Integer> stock){
        return stock.get(Ingredients.MILK_FOAM) <= MINIMUM_MILK_RELATED_STOCK ||
                stock.get(Ingredients.STEAMED_MILK) <= MINIMUM_MILK_RELATED_STOCK;
    }

    private boolean notEnoughAdditionalIngredients(Map<Ingredients, Integer> stock){
        return stock.get(Ingredients.HONEY) <= MINIMUM_ADDITIONAL_INGREDIENTS_STOCK ||
                stock.get(Ingredients.CINNAMON) <= MINIMUM_ADDITIONAL_INGREDIENTS_STOCK ||
                stock.get(Ingredients.SYRUP) <= MINIMUM_ADDITIONAL_INGREDIENTS_STOCK;
    }
}
