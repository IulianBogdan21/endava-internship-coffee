package com.coffeeshop.utilitary;

import java.util.HashMap;
import java.util.Map;

/**
 * class used to initially build the stock of supplies (at the beginning of the day)
 */
public class StockBuilder {

    public static Map<Ingredients, Integer> buildInitialStocks(){
        Map<Ingredients, Integer> stocksPerDay = new HashMap<>();
        stocksPerDay.put(Ingredients.ESPRESSO, 20);
        stocksPerDay.put(Ingredients.BLACK_COFFEE, 20);
        stocksPerDay.put(Ingredients.CINNAMON, 10);
        stocksPerDay.put(Ingredients.HONEY, 10);
        stocksPerDay.put(Ingredients.MILK_FOAM, 15);
        stocksPerDay.put(Ingredients.STEAMED_MILK, 15);
        stocksPerDay.put(Ingredients.SYRUP, 10);
        return stocksPerDay;
    }
}
