import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        Map<Ingredients, Double> pricesForEachIngredient = PricesBuilder.buildPricesForIngredients();
        CoffeeShop openCoffeeShop = new CoffeeShop();
        while (true){
            System.out.println("----------" + openCoffeeShop.getCoffeeShopName() + "----------" + "\n");
            System.out.println("Introduce your name: ");
            String customerName = scanner.nextLine();
            System.out.println("\n");
            System.out.println("Pickup or delivery?" + "\n");
            System.out.println("1: Pickup");
            System.out.println("2: Delivery");
            System.out.println("Introduce option: ");
            int statusOption = scanner.nextInt();
            scanner.nextLine();
            OrderStatus orderStatus = null;
            if(statusOption == 1) {
                orderStatus = OrderStatus.PICKUP;
            }
            else if(statusOption == 2) {
                orderStatus = OrderStatus.DELIVERY;
            }
            System.out.println("\n");
            System.out.println("Select the coffees you want. Introduce the number for the coffees you want and then the quantity. " +
                    "When your order is done, introduce 0" + "\n");
            CoffeeOrder coffeeOrder = new CoffeeOrder(orderStatus);
            while (true){
                System.out.println(openCoffeeShop.getAllBeverages() + "\n");
                System.out.println("Introduce option: ");
                int optionRead = scanner.nextInt();
                scanner.nextLine();
                Coffee orderedCoffee = switch (optionRead) {
                    case 1 -> new Espresso(customerName);
                    case 2 -> new Machiatto(customerName);
                    case 3 -> new CoffeeLatte(customerName);
                    case 4 -> new Cappucino(customerName);
                    case 5 -> new CoffeeMiel(customerName);
                    default -> null;
                };
                if(optionRead == 0){
                    System.out.println("You ordered the following:\n");
                    for(Coffee coffee: coffeeOrder.getOrderedCoffeesAndQuantity().keySet()){
                        System.out.println(String.valueOf(coffeeOrder.getOrderedCoffeesAndQuantity().get(coffee)) +
                                "X " + coffee.getCoffeeName() + " where 1 " + coffee.getCoffeeName() + " is " +
                                String.valueOf(coffee.getPrice(pricesForEachIngredient)) + " euros");
                    }
                    double addedProfit = coffeeOrder.getPriceOfOrder(pricesForEachIngredient);
                    System.out.println("\nTotal cost is: " + String.valueOf(addedProfit));
                    openCoffeeShop.addToProfit(addedProfit);
                    System.out.println("Shop's current profit is: " + String.valueOf(openCoffeeShop.getProfit()) + "\n");
                    break;
                }
                if(orderedCoffee == null){
                    System.out.println("You did not introduce a valid option. Try again!\n");
                    continue;
                }
                System.out.println("\nIntroduce quantity: ");
                int quantity = scanner.nextInt();
                System.out.println("\nYou added to the order " + String.valueOf(quantity) + " " + orderedCoffee.getCoffeeName());
                coffeeOrder.addCoffeeToOrder(orderedCoffee, quantity);
            }
        }
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
        super(customerName);
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
        super(customerName);
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
        super(customerName);
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
        super(customerName);
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

    public Map<Coffee, Integer> getOrderedCoffeesAndQuantity() {
        return orderedCoffeesAndQuantity;
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
    private Double profit;

    CoffeeShop(){
        this.coffeeShopName = "Good to go";
        this.profit = 0.0;
        allCoffees = new ArrayList<>();
        allCoffees.add(new Espresso());
        allCoffees.add(new Machiatto());
        allCoffees.add(new CoffeeLatte());
        allCoffees.add(new Cappucino());
        allCoffees.add(new CoffeeMiel());
    }

    public String getCoffeeShopName(){
        return coffeeShopName;
    }

    public Double getProfit() {
        return profit;
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

    public void addToProfit(double sumToAdd){
        this.profit += sumToAdd;
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