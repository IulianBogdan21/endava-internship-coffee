package domain;

import java.util.ArrayList;
import java.util.List;

public class CoffeeShop {
    private String coffeeShopName;
    private List<Coffee> allCoffees;
    private Double profit;

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

    public StringBuilder getAllBeverages() {
        StringBuilder allBeverages = new StringBuilder();
        for (int i = 0; i < allCoffees.size(); i++) {
            allBeverages.append(String.valueOf(i + 1));
            allBeverages.append(": ");
            allBeverages.append(allCoffees.get(i).getCoffeeName());
            allBeverages.append("\n");
        }
        return allBeverages;
    }

    public void addToProfit(double sumToAdd) {
        this.profit += sumToAdd;
    }
}
