package com.coffeeshop.domain;

import com.coffeeshop.utilitary.Ingredients;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeShop {
    private String coffeeShopName;
    private List<Coffee> allCoffees;
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
}
