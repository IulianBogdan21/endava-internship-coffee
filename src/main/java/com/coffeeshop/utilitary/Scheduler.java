package com.coffeeshop.utilitary;


import com.coffeeshop.domain.CoffeeShop;
import com.coffeeshop.service.IngredientsService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Scheduler {

    /**
     * every 20 seconds, this function realises an inventory check on the shop's ingredients
     */
    @Scheduled(fixedDelay = 1000 * 60, initialDelay = 1000 * 10)
    public static void scheduleInventoryCheck(){
        Map<Ingredients, Integer> stock = ApplicationContextFactory.getInstance()
                .getBean("ingredientsService", IngredientsService.class).getAllIngredients();
        Map<Ingredients, String> nameOfIngredients = CoffeeShop.getNameOfIngredients();
        MessagePrinter.printLines();
        for(Ingredients iteratedIngredient: stock.keySet()){
            MessagePrinter.printNameOfIngredientAndQuantity(stock, iteratedIngredient, nameOfIngredients);
        }
        MessagePrinter.printLines();
    }
}
