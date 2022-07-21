import domain.*;
import utilitary.Ingredients;
import utilitary.PricesBuilder;

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

