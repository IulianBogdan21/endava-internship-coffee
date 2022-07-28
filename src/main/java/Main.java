import domain.*;
import org.jetbrains.annotations.Nullable;
import utilitary.*;

import java.util.*;

public class Main {

    private static final int DELIVERY_STATUS_LOWER_LIMIT = 1;
    private static final int DELIVERY_STATUS_HIGHER_LIMIT = 2;
    private static final int COFFEE_TYPE_LOWER_LIMIT = 0;
    private static final int COFFEE_TYPE_HIGHER_LIMIT = 6;
    private static final int FINISH_ORDER = 0;

    public static void main(String[] args) {
        Scanner scanner = createScanner();
        Map<Ingredients, Double> pricesForEachIngredient = PricesBuilder.buildPricesForIngredients();
        CoffeeShop coffeeShop = openCoffeeShop();
        handleOrdersFromClients(coffeeShop, pricesForEachIngredient, scanner);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private static void handleOrdersFromClients(CoffeeShop coffeeShop, Map<Ingredients, Double> pricesForEachIngredient,
                                                Scanner scanner){
        while(true){
            MessagePrinter.printCoffeeShopName(coffeeShop);
            String customerName = registerCustomerName(scanner);
            CoffeeOrder coffeeOrder = createNewCoffeeOrder(scanner);
            getOrderFromClient(coffeeShop, customerName, coffeeOrder, pricesForEachIngredient, scanner);
        }
    }

    private static Scanner createScanner(){
        return new Scanner(System.in);
    }

    private static CoffeeShop openCoffeeShop(){
        return new CoffeeShop();
    }

    private static boolean optionChosenFromMenuIsInvalid(Coffee orderedCoffee){
        return orderedCoffee == null;
    }

    private static CoffeeOrder createNewCoffeeOrder(Scanner scanner){
        MessagePrinter.printOptionsForOrderStatus();
        int statusOption = NumberGenerator.generateAndValidateIntegerFromCertainInterval(
                scanner, DELIVERY_STATUS_LOWER_LIMIT, DELIVERY_STATUS_HIGHER_LIMIT);
        OrderStatus orderStatus = getStatusBasedOnChosenOption(statusOption);
        MessagePrinter.printAdditionalInformationAboutTheMenu();
        return new CoffeeOrder(orderStatus);
    }

    private static void finishCustomerOrder(CoffeeShop coffeeShop, CoffeeOrder coffeeOrder,
                                            Map<Ingredients, Double> pricesForEachIngredient){
        MessagePrinter.printOrderedCoffeesAndTheirAmount(pricesForEachIngredient, coffeeOrder);
        double profitObtained = coffeeOrder.getPriceOfOrder(pricesForEachIngredient);
        MessagePrinter.printCostOfOrder(profitObtained);
        coffeeShop.addToProfit(profitObtained);
        MessagePrinter.printCurrentProfitOfCoffeeShop(coffeeShop);
    }

    private static int getAmountOfOrderedCoffee(Scanner scanner){
        MessagePrinter.printQuestionHowManyOfTheChosenCoffeeDoesTheCustomerWant();
        return NumberGenerator.generateAndValidateIntegerWithNoIntervalConstraints(scanner);
    }

    private static void updateCoffeeOrder(CoffeeOrder coffeeOrder, Coffee orderedCoffee, int amountOfCoffee){
        MessagePrinter.printUpdatedOrder(orderedCoffee, amountOfCoffee);
        coffeeOrder.addCoffeeToOrder(orderedCoffee, amountOfCoffee);
    }

    private static void getOrderFromClient(CoffeeShop coffeeShop, String customerName, CoffeeOrder coffeeOrder,
                                           Map<Ingredients, Double> pricesForEachIngredient, Scanner scanner){
        while(true){
            MessagePrinter.printMenu(coffeeShop);
            int menuOption = NumberGenerator.generateAndValidateIntegerFromCertainInterval(
                    scanner, COFFEE_TYPE_LOWER_LIMIT, COFFEE_TYPE_HIGHER_LIMIT);
            Coffee orderedCoffee = CoffeeBuilder.buildCoffeeFromMenu(menuOption, customerName, scanner);
            if (menuOption == FINISH_ORDER) {
                finishCustomerOrder(coffeeShop, coffeeOrder, pricesForEachIngredient);
                return;
            }
            if (optionChosenFromMenuIsInvalid(orderedCoffee)) {
                MessagePrinter.printMenuInvalidOption();
                continue;
            }
            int amountOfCoffee = getAmountOfOrderedCoffee(scanner);
            updateCoffeeOrder(coffeeOrder, orderedCoffee, amountOfCoffee);
        }
    }

    /**
     * @param statusOption Integer equal to 1 or 2
     * @return PICKUP OrderStatus if integer 1, DELIVERY OrderStatus if integer 2
     */
    @Nullable
    private static OrderStatus getStatusBasedOnChosenOption(int statusOption) {
        OrderStatus orderStatus = null;
        if(statusOption == 1) {
            orderStatus = OrderStatus.PICKUP;
        }
        else if(statusOption == 2) {
            orderStatus = OrderStatus.DELIVERY;
        }
        return orderStatus;
    }

    /**
     * @param scanner Scanner
     * @return String representing the customer's name
     */
    private static String registerCustomerName(Scanner scanner) {
        System.out.println("Introduce your name: ");
        String customerName = scanner.nextLine();
        System.out.println("\n");
        return customerName;
    }
}

