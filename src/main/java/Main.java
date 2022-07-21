import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<Ingredients, Double> pricesForEachIngredient = PricesBuilder.buildPricesForIngredients();
    }
}

class PricesBuilder{
    public static Map<Ingredients, Double> buildPricesForIngredients(){
        Map<Ingredients, Double> pricesForEachIngredient = new HashMap<Ingredients, Double>();
        pricesForEachIngredient.put(Ingredients.ESPRESSO, 2.5);
        pricesForEachIngredient.put(Ingredients.BLACK_COFFEE, 1.5);
        pricesForEachIngredient.put(Ingredients.CINNAMON, 2.0);
        pricesForEachIngredient.put(Ingredients.HONEY, 3.1);
        pricesForEachIngredient.put(Ingredients.MILK_FOAM, 3.5);
        pricesForEachIngredient.put(Ingredients.STEAMED_MILK, 3.6);
        return pricesForEachIngredient;
    }
}

abstract class Coffee{
    protected String customerName;
    protected Map<Ingredients, Integer> ingredientsForCoffeeAndAmount = new HashMap<>();

    public double getPrice(Map<Ingredients, Double> ingredientsPrices){
        double finalPriceOfCoffee = 0;
        for(Ingredients ingredient : ingredientsForCoffeeAndAmount.keySet())
            finalPriceOfCoffee += ingredientsForCoffeeAndAmount.get(ingredient) * ingredientsPrices.get(ingredient);
        return finalPriceOfCoffee;
    }

    public String getCustomerName(){
        return this.customerName;
    }

    public abstract String getCoffeeName();
}

class Espresso extends Coffee{

    public Espresso(){}

    public Espresso(String customerName) {
        ingredientsForCoffeeAndAmount.put(Ingredients.ESPRESSO, 1);
        this.customerName = customerName;
    }

    public String getCoffeeName(){
        return "Espresso";
    }
}

class BlackCoffee extends Coffee{

    public BlackCoffee(){}

    public BlackCoffee(String customerName) {
        ingredientsForCoffeeAndAmount.put(Ingredients.BLACK_COFFEE, 1);
        this.customerName = customerName;
    }

    public String getCoffeeName(){
        return "Black coffee";
    }
}

class Machiatto extends Espresso{

    public Machiatto(){}

    public Machiatto(String customerName) {
        super();
        ingredientsForCoffeeAndAmount.put(Ingredients.MILK_FOAM, 1);
        this.customerName = customerName;
    }

    public String getCoffeeName(){
        return "Machiatto";
    }
}

class CoffeeLatte extends Espresso{

    public CoffeeLatte(){}

    public CoffeeLatte(String customerName) {
        super();
        ingredientsForCoffeeAndAmount.put(Ingredients.STEAMED_MILK, 2);
        ingredientsForCoffeeAndAmount.put(Ingredients.MILK_FOAM, 1);
        this.customerName = customerName;
    }

    public String getCoffeeName(){
        return "Coffee Latte";
    }
}

class Cappucino extends Espresso{

    public Cappucino(){}

    public Cappucino(String customerName) {
        super();
        ingredientsForCoffeeAndAmount.put(Ingredients.STEAMED_MILK, 1);
        ingredientsForCoffeeAndAmount.put(Ingredients.MILK_FOAM, 2);
        this.customerName = customerName;
    }

    public String getCoffeeName(){
        return "Cappucino";
    }
}

class CoffeeMiel extends BlackCoffee{

    public CoffeeMiel(){}

    public CoffeeMiel(String customerName) {
        super();
        ingredientsForCoffeeAndAmount.put(Ingredients.HONEY, 1);
        ingredientsForCoffeeAndAmount.put(Ingredients.CINNAMON, 1);
        ingredientsForCoffeeAndAmount.put(Ingredients.STEAMED_MILK, 1);
        this.customerName = customerName;
    }

    public String getCoffeeName(){
        return "Coffee Miel";
    }
}

class CoffeeOrder{
    private OrderStatus orderStatus;
    private Map<Coffee, Integer> orderedCoffeesAndQuantity;

    CoffeeOrder(){
        orderedCoffeesAndQuantity = new HashMap<Coffee, Integer>();
    }

    CoffeeOrder(OrderStatus orderStatus){
        this.orderStatus = orderStatus;
        orderedCoffeesAndQuantity = new HashMap<Coffee, Integer>();
    }

    public void addCoffeeToOrder(Coffee coffeeToAdd, Integer quantity){
        orderedCoffeesAndQuantity.put(coffeeToAdd, quantity);
    }

    public double getPriceOfOrder(Map<Ingredients, Double> ingredientsAndTheirPrices){
        double orderTotalPay = 0;
        for(Coffee coffee: orderedCoffeesAndQuantity.keySet()){
            orderTotalPay += orderedCoffeesAndQuantity.get(coffee) * coffee.getPrice(ingredientsAndTheirPrices);
        }
        return orderTotalPay;
    }
}

class CoffeeShop{
    private String coffeeShopName;
    private List<Coffee> allCoffees;
    private Integer profit;

    CoffeeShop(){
        this.coffeeShopName = "Good to go";
        this.profit = 0;
        allCoffees = new ArrayList<>();
        allCoffees.add(new Espresso());
        allCoffees.add(new BlackCoffee());
        allCoffees.add(new CoffeeLatte());
        allCoffees.add(new Cappucino());
        allCoffees.add(new CoffeeMiel());
    }

    public String getCoffeeShopName(){
        return coffeeShopName;
    }

    public StringBuilder getAllBeverages(){
        StringBuilder allBeverages = new StringBuilder();
        for(int i = 0; i < allCoffees.size(); i++){
            allBeverages.append(String.valueOf(i + 1));
            allBeverages.append(": ");
            allBeverages.append(allCoffees.get(i).getCoffeeName());
            allBeverages.append("\n");
        }
        return allBeverages;
    }
}

enum Ingredients{
    ESPRESSO,
    BLACK_COFFEE,
    MILK_FOAM,
    STEAMED_MILK,
    HONEY,
    CINNAMON
}

enum OrderStatus{
    PICKUP,
    DELIVERY
}