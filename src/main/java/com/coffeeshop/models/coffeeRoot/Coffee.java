package com.coffeeshop.models.coffeeRoot;

import com.coffeeshop.models.shop.Ingredients;
import com.coffeeshop.utilitary.factories.ApplicationContextFactory;
import com.coffeeshop.utilitary.managers.PricesManager;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * abstract class Coffee - further coffee types will inherit this class
 */
//@MappedSuperclass
@Entity
@Table(name = "recipes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Coffee {
    @Id
    @Column(name = "name")
    protected String coffeeName;
    @Transient
    protected String customerName;
    @ElementCollection(fetch = FetchType.EAGER)
    @OrderColumn
    @MapKeyEnumerated(EnumType.STRING)
    protected Map<Ingredients, Integer> recipe = new HashMap<Ingredients, Integer>();

    /**
     * @return double = the price of the coffee (in euros)
     */
    public double getPrice() {
        Map<Ingredients, Double> ingredientsPrices = ApplicationContextFactory.getInstance()
                .getBean("pricesManager", PricesManager.class).getPricesForIngredients();
        double finalPriceOfCoffee = 0;
        for (Ingredients ingredient : recipe.keySet())
            finalPriceOfCoffee += recipe.get(ingredient) * ingredientsPrices.get(ingredient);
        return finalPriceOfCoffee;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public Map<Ingredients, Integer> getRecipe(){
        return recipe;
    }

    public abstract String getCoffeeName();

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coffee coffee = (Coffee) o;
        return Objects.equals(customerName, coffee.customerName) && Objects.equals(recipe, coffee.recipe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerName, recipe);
    }
}
