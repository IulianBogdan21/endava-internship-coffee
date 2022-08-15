package com.coffeeshop.models.shop;

import com.coffeeshop.models.coffeeRoot.Coffee;
import com.coffeeshop.models.defaultCoffees.*;
import com.coffeeshop.service.implementations.IngredientsService;
import com.coffeeshop.utilitary.factories.ApplicationContextFactory;
import com.coffeeshop.utilitary.printers.Printer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CoffeeShop {
    private final String coffeeShopName;
    private final List<Coffee> allCoffees;
    private Double profit;
    private static Map<Ingredients, String> nameOfIngredients;

    public CoffeeShop() {
        this.coffeeShopName = "Good to go";
        this.profit = 0.0;
        allCoffees = new ArrayList<>();
        allCoffees.add(new Espresso());
        allCoffees.add(new Machiatto());
        allCoffees.add(new CoffeeLatte());
        allCoffees.add(new Cappucino());
        allCoffees.add(new CoffeeMiel());
    }

    public String getCoffeeShopName() {
        return coffeeShopName;
    }

    public Double getProfit() {
        return profit;
    }

    /**
     * @return String (one under another : list format) - all the beverages that the shop sells
     */
    public StringBuilder getAllBeverages() {
        StringBuilder allBeverages = new StringBuilder();
        for (int i = 0; i < allCoffees.size(); i++) {
            allBeverages.append(i + 1);
            allBeverages.append(": ");
            allBeverages.append(allCoffees.get(i).getCoffeeName());
            allBeverages.append("\n");
        }
        return allBeverages;
    }

    /**
     * adds the profit from a customer's order to the shop's profit
     * @param sumToAdd integer = the profit to be added to the current one
     */
    public void addToProfit(double sumToAdd) {
        this.profit += sumToAdd;
    }

    /**
     * @return a map with a string equivalent for every enum Ingredient
     */
    public static Map<Ingredients, String> getNameOfIngredients(){
        if(nameOfIngredients == null){
            nameOfIngredients = new HashMap<>();
            nameOfIngredients.put(Ingredients.ESPRESSO, "espresso");
            nameOfIngredients.put(Ingredients.BLACK_COFFEE, "black coffee");
            nameOfIngredients.put(Ingredients.STEAMED_MILK, "steamed milk");
            nameOfIngredients.put(Ingredients.MILK_FOAM, "milk foam");
            nameOfIngredients.put(Ingredients.CINNAMON, "cinnamon");
            nameOfIngredients.put(Ingredients.HONEY, "honey");
            nameOfIngredients.put(Ingredients.SYRUP, "syrup");
        }
        return nameOfIngredients;
    }

    /**
     * every 20 seconds, this function realises an inventory check on the shop's ingredients
     */
    @Scheduled(fixedDelay = 1000 * 20, initialDelay = 1000 * 10)
    public void scheduleInventoryCheck(){
        IngredientsService ingredientsService = ApplicationContextFactory.getInstance()
                .getBean("ingredientsService", IngredientsService.class);
        Map<Ingredients, Integer> stock = ingredientsService.getAllIngredients();
        Map<Ingredients, String> nameOfIngredients = getNameOfIngredients();
        if(ingredientsService.areSuppliesLow(stock)) {
            Printer printer = ApplicationContextFactory.getInstance().getBean("printer", Printer.class);
            printer.printLines();
            printer.printWarningMessage();
            printer.printStockMessage();
            for (Ingredients iteratedIngredient : stock.keySet()) {
                printer.printNameOfIngredientAndQuantity(stock, iteratedIngredient, nameOfIngredients);
            }
            printer.printLines();
        }
    }
}
