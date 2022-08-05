import com.coffeeshop.models.coffeeRoot.Coffee;
import com.coffeeshop.models.customer.CoffeeOrder;
import com.coffeeshop.models.shop.CoffeeShop;
import com.coffeeshop.service.implementations.IngredientsService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.coffeeshop.utilitary.*;

import java.util.*;

public class Main {

    private static final int DELIVERY_STATUS_LOWER_LIMIT = 1;
    private static final int DELIVERY_STATUS_HIGHER_LIMIT = 2;
    private static final int COFFEE_TYPE_LOWER_LIMIT = 0;
    private static final int COFFEE_TYPE_HIGHER_LIMIT = 6;
    private static final int FINISH_ORDER = 0;

    public static void main(String[] args) {
        CoffeeShop coffeeShop = openCoffeeShop();
        coffeeShop.scheduleInventoryCheck();
        handleOrdersFromClients(coffeeShop);
    }

    /**
     * function that handles customer requests in an infinite loop
     * @param coffeeShop - CoffeeShop
     */
    @SuppressWarnings("InfiniteLoopStatement")
    private static void handleOrdersFromClients(CoffeeShop coffeeShop){
        while(true){
            MessagePrinter.printCoffeeShopName(coffeeShop);
            String customerName = registerCustomerName();
            CoffeeOrder coffeeOrder = createNewCoffeeOrder();
            getOrderFromClient(coffeeShop, customerName, coffeeOrder);
        }
    }

    /**
     * @return new instance of CoffeeShop class
     */
    @Contract(" -> new")
    private static @NotNull CoffeeShop openCoffeeShop(){
        return new CoffeeShop();
    }

    /**
     * @param orderedCoffee - Coffee that has been ordered
     * @return true if a valid option has been chosen from the menu, false otherwise
     */
    @Contract(value = "null -> true; !null -> false", pure = true)
    private static boolean optionChosenFromMenuIsInvalid(Coffee orderedCoffee){
        return orderedCoffee == null;
    }

    /**
     * @return a new order from a client that also contains its status(pickup or delivery)
     */
    private static @NotNull CoffeeOrder createNewCoffeeOrder(){
        MessagePrinter.printOptionsForOrderStatus();
        int statusOption = NumberGenerator.generateIntegerWithinInterval(DELIVERY_STATUS_LOWER_LIMIT,
                DELIVERY_STATUS_HIGHER_LIMIT);
        OrderStatus orderStatus = getStatusBasedOnChosenOption(statusOption);
        MessagePrinter.printAdditionalInformationAboutTheMenu();
        return new CoffeeOrder(orderStatus);
    }

    /**
     * function that finishes a customer's order and updates the profit
     * @param coffeeShop CoffeeShop
     * @param coffeeOrder - customer's order
     */
    private static void finishCustomerOrder(@NotNull CoffeeShop coffeeShop, CoffeeOrder coffeeOrder){
        MessagePrinter.printOrderedCoffeesAndTheirAmount(coffeeOrder);
        double profitObtained = coffeeOrder.getPriceOfOrder();
        MessagePrinter.printCostOfOrder(profitObtained);
        coffeeShop.addToProfit(profitObtained);
        MessagePrinter.printCurrentProfitOfCoffeeShop(coffeeShop);
    }

    /**
     * @return integer = number of coffees of a certain kind (the ordered one)
     */
    private static int getAmountOfOrderedCoffee(){
        MessagePrinter.printQuestionHowManyOfTheChosenCoffeeDoesTheCustomerWant();
        return NumberGenerator.generateInteger();
    }

    /**
     * function that adds a coffee to a customer's order
     * @param coffeeOrder - order made by a customer
     * @param orderedCoffee - coffee chosen by the customer to be added to order
     * @param amountOfCoffee - integer - number of coffees of a chosen option
     */
    private static void updateCoffeeOrder(@NotNull CoffeeOrder coffeeOrder, Coffee orderedCoffee, int amountOfCoffee){
        MessagePrinter.printUpdatedOrder(orderedCoffee, amountOfCoffee);
        coffeeOrder.addCoffeeToOrder(orderedCoffee, amountOfCoffee);
    }

    /**
     * @param coffeeShop CoffeeShop
     * @param customerName - the name of the customer
     * @param coffeeOrder - the customer's order
     */
    private static void getOrderFromClient(CoffeeShop coffeeShop, String customerName, CoffeeOrder coffeeOrder){
        while(true){
            MessagePrinter.printMenu(coffeeShop);
            int menuOption = NumberGenerator.generateIntegerWithinInterval(COFFEE_TYPE_LOWER_LIMIT,
                    COFFEE_TYPE_HIGHER_LIMIT);
            Coffee orderedCoffee = CoffeeManager.buildCoffeeFromMenu(menuOption, customerName);
            if (menuOption == FINISH_ORDER) {
                finishCustomerOrder(coffeeShop, coffeeOrder);
                return;
            }
            if (optionChosenFromMenuIsInvalid(orderedCoffee)) {
                MessagePrinter.printMenuInvalidOption();
                continue;
            }
            int amountOfCoffee = getAmountOfOrderedCoffee();
            updateCoffeeOrder(coffeeOrder, orderedCoffee, amountOfCoffee);
            ApplicationContextFactory.getInstance().getBean("ingredientsService", IngredientsService.class)
                    .updateStock(StockManager.evaluateAllIngredientsPerCoffeeCommand(orderedCoffee.getIngredientsForCoffeeAndAmount(), amountOfCoffee));
        }
    }

    /**
     * @param statusOption Integer equal to 1 or 2
     * @return PICKUP OrderStatus if integer is 1, DELIVERY OrderStatus if integer is 2
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
     * @return String representing the customer's name
     */
    private static String registerCustomerName() {
        MessagePrinter.printRegisteringNameMessage();
        String customerName = ApplicationContextFactory.getInstance().getBean("scanner", Scanner.class).nextLine();
        MessagePrinter.printNewLine();
        return customerName;
    }
}

