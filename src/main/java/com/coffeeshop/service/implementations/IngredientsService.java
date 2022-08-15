package com.coffeeshop.service.implementations;

import com.coffeeshop.models.dtos.IngredientDto;
import com.coffeeshop.repository.implementations.IngredientsRepository;
import com.coffeeshop.models.shop.Ingredients;
import com.coffeeshop.service.interfaces.CoffeeShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("ingredientsService")
public class IngredientsService implements CoffeeShopService{

    private IngredientsRepository ingredientsRepository;

    private static final int MINIMUM_BASE_COFFEE_STOCK = 9;
    private static final int MINIMUM_MILK_RELATED_STOCK = 6;
    private static final int MINIMUM_ADDITIONAL_INGREDIENTS_STOCK = 3;

    public IngredientsService(){}

    @Autowired
    public IngredientsService(IngredientsRepository ingredientsRepository){
        this.ingredientsRepository = ingredientsRepository;
    }

    public Map<Ingredients, Integer> getAllIngredients() throws Exception {
        List<IngredientDto> listWithIngredientsAndAmount = ingredientsRepository.getAll();
        Map<Ingredients, Integer> mapWithAllIngredientsAndAmount = new HashMap<>();
        for(IngredientDto ingredient: listWithIngredientsAndAmount){
            mapWithAllIngredientsAndAmount.put(ingredient.getIngredient(), ingredient.getQuantity());
        }
        return mapWithAllIngredientsAndAmount;
    }

    /**
     * @param consumedIngredients a map with all the ingredients consumed for an ordered coffee and amount
     * @throws Exception - exceptions when loading and updating into the database
     */
    public void updateStock(Map<Ingredients, Integer> consumedIngredients) throws Exception {
        for(Ingredients ingredient: consumedIngredients.keySet()){
            IngredientDto ingredientToUpdateAndAmount =
                    new IngredientDto(ingredient, consumedIngredients.get(ingredient));
            ingredientsRepository.update(ingredientToUpdateAndAmount);
        }
    }

    /**
     * @param stock - current stock of ingredients
     * @return true if the shop is low on some ingredients, false otherwise
     */
    public boolean areSuppliesLow(Map<Ingredients, Integer> stock){
        return notEnoughBaseIngredients(stock) || notEnoughMilkIngredients(stock) || notEnoughAdditionalIngredients(stock);
    }

    /**
     * @param stock - current stock of ingredients
     * @return true if the shop is low on base ingredients, false otherwise
     */
    private boolean notEnoughBaseIngredients(Map<Ingredients, Integer> stock){
        return stock.get(Ingredients.ESPRESSO) <= MINIMUM_BASE_COFFEE_STOCK ||
                stock.get(Ingredients.BLACK_COFFEE) <= MINIMUM_BASE_COFFEE_STOCK;
    }

    /**
     * @param stock - current stock of ingredients
     * @return true if the shop is low on milk related ingredients, false otherwise
     */
    private boolean notEnoughMilkIngredients(Map<Ingredients, Integer> stock){
        return stock.get(Ingredients.MILK_FOAM) <= MINIMUM_MILK_RELATED_STOCK ||
                stock.get(Ingredients.STEAMED_MILK) <= MINIMUM_MILK_RELATED_STOCK;
    }

    /**
     * @param stock - current stock of ingredients
     * @return true if the shop is low on additional ingredients and sweeteners, false otherwise
     */
    private boolean notEnoughAdditionalIngredients(Map<Ingredients, Integer> stock){
        return stock.get(Ingredients.HONEY) <= MINIMUM_ADDITIONAL_INGREDIENTS_STOCK ||
                stock.get(Ingredients.CINNAMON) <= MINIMUM_ADDITIONAL_INGREDIENTS_STOCK ||
                stock.get(Ingredients.SYRUP) <= MINIMUM_ADDITIONAL_INGREDIENTS_STOCK;
    }
}
