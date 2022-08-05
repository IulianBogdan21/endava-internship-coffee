package com.coffeeshop.models.coffeeRoot;

import com.coffeeshop.models.shop.Ingredients;
import com.coffeeshop.utilitary.manager.PricesManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * abstract class Coffee - further coffee types will inherit this class
 */
public abstract class Coffee {
    protected String customerName;
    protected Map<Ingredients, Integer> ingredientsForCoffeeAndAmount = new HashMap<>();

    /**
     * @return double = the price of the coffee (in euros)
     */
    public double getPrice() {
        Map<Ingredients, Double> ingredientsPrices = PricesManager.getPricesForIngredients();
        double finalPriceOfCoffee = 0;
        for (Ingredients ingredient : ingredientsForCoffeeAndAmount.keySet())
            finalPriceOfCoffee += ingredientsForCoffeeAndAmount.get(ingredient) * ingredientsPrices.get(ingredient);
        return finalPriceOfCoffee;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public Map<Ingredients, Integer> getIngredientsForCoffeeAndAmount(){
        return ingredientsForCoffeeAndAmount;
    }

    public abstract String getCoffeeName();

    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coffee coffee = (Coffee) o;
        return Objects.equals(customerName, coffee.customerName) && Objects.equals(ingredientsForCoffeeAndAmount, coffee.ingredientsForCoffeeAndAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerName, ingredientsForCoffeeAndAmount);
    }
}
