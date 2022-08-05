package com.coffeeshop;

import com.coffeeshop.models.coffeeRoot.Coffee;
import com.coffeeshop.models.customer.Card;
import com.coffeeshop.models.customer.CoffeeOrder;
import com.coffeeshop.models.customer.OrderStatus;
import com.coffeeshop.models.customer.Payment;
import com.coffeeshop.models.shop.CoffeeShop;
import com.coffeeshop.rest.RestClient;
import com.coffeeshop.service.implementations.IngredientsService;
import com.coffeeshop.utilitary.factories.ApplicationContextFactory;
import com.coffeeshop.utilitary.generators.IdGenerator;
import com.coffeeshop.utilitary.generators.NumberGenerator;
import com.coffeeshop.utilitary.manager.CoffeeManager;
import com.coffeeshop.utilitary.manager.StockManager;
import com.coffeeshop.utilitary.printers.Printer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@SpringBootApplication
public class Main {

    private static final int DELIVERY_STATUS_LOWER_LIMIT = 1;
    private static final int DELIVERY_STATUS_HIGHER_LIMIT = 2;
    private static final int COFFEE_TYPE_LOWER_LIMIT = 0;
    private static final int COFFEE_TYPE_HIGHER_LIMIT = 6;
    private static final int FINISH_ORDER = 0;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
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
            ApplicationContextFactory.getInstance().getBean("printer", Printer.class).printCoffeeShopName(coffeeShop);
            String customerName = registerCustomerName();
            CoffeeOrder coffeeOrder = createNewCoffeeOrder(customerName);
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
     * @param customerName - String - name of the customer
     * @return a new order from a client that also contains its status(pickup or delivery)
     */
    private static @NotNull CoffeeOrder createNewCoffeeOrder(String customerName){
        ApplicationContextFactory.getInstance().getBean("printer", Printer.class).printOptionsForOrderStatus();
        int statusOption = NumberGenerator.generateIntegerWithinInterval(DELIVERY_STATUS_LOWER_LIMIT,
                DELIVERY_STATUS_HIGHER_LIMIT);
        OrderStatus orderStatus = getStatusBasedOnChosenOption(statusOption);
        ApplicationContextFactory.getInstance().getBean("printer", Printer.class).printAdditionalInformationAboutTheMenu();
        return new CoffeeOrder(orderStatus, customerName);
    }

    /**
     * function that finishes a customer's order and updates the profit
     * @param coffeeShop CoffeeShop
     * @param coffeeOrder - customer's order
     */
    private static void finishCustomerOrder(@NotNull CoffeeShop coffeeShop, CoffeeOrder coffeeOrder){
        ApplicationContextFactory.getInstance().getBean("printer", Printer.class).printOrderedCoffeesAndTheirAmount(coffeeOrder);
        double profitObtained = coffeeOrder.getPriceOfOrder();
        ApplicationContextFactory.getInstance().getBean("printer", Printer.class).printCostOfOrder(profitObtained);
        coffeeShop.addToProfit(profitObtained);
        ApplicationContextFactory.getInstance().getBean("printer", Printer.class).printCurrentProfitOfCoffeeShop(coffeeShop);
    }

    /**
     * @return integer = number of coffees of a certain kind (the ordered one)
     */
    private static int getAmountOfOrderedCoffee(){
        ApplicationContextFactory.getInstance().getBean("printer", Printer.class).printQuestionHowManyOfTheChosenCoffeeDoesTheCustomerWant();
        return NumberGenerator.generateInteger();
    }

    /**
     * function that adds a coffee to a customer's order
     * @param coffeeOrder - order made by a customer
     * @param orderedCoffee - coffee chosen by the customer to be added to order
     * @param amountOfCoffee - integer - number of coffees of a chosen option
     */
    private static void updateCoffeeOrder(@NotNull CoffeeOrder coffeeOrder, Coffee orderedCoffee, int amountOfCoffee){
        ApplicationContextFactory.getInstance().getBean("printer", Printer.class).printUpdatedOrder(orderedCoffee, amountOfCoffee);
        coffeeOrder.addCoffeeToOrder(orderedCoffee, amountOfCoffee);
    }

    /**
     * @param coffeeShop CoffeeShop
     * @param customerName - the name of the customer
     * @param coffeeOrder - the customer's order
     */
    private static void getOrderFromClient(CoffeeShop coffeeShop, String customerName, CoffeeOrder coffeeOrder){
        while(true){
            ApplicationContextFactory.getInstance().getBean("printer", Printer.class).printMenu(coffeeShop);
            int menuOption = NumberGenerator.generateIntegerWithinInterval(COFFEE_TYPE_LOWER_LIMIT,
                    COFFEE_TYPE_HIGHER_LIMIT);
            Coffee orderedCoffee = CoffeeManager.buildCoffeeFromMenu(menuOption, customerName);
            if (menuOption == FINISH_ORDER) {
                proceedWithPayment(coffeeOrder);
                finishCustomerOrder(coffeeShop, coffeeOrder);
                return;
            }
            if (optionChosenFromMenuIsInvalid(orderedCoffee)) {
                ApplicationContextFactory.getInstance().getBean("printer", Printer.class).printMenuInvalidOption();
                continue;
            }
            int amountOfCoffee = getAmountOfOrderedCoffee();
            updateCoffeeOrder(coffeeOrder, orderedCoffee, amountOfCoffee);
            ApplicationContextFactory.getInstance().getBean("ingredientsService", IngredientsService.class)
                    .updateStock(StockManager.evaluateAllIngredientsPerCoffeeCommand(orderedCoffee.getIngredientsForCoffeeAndAmount(), amountOfCoffee));
        }
    }

    private static void proceedWithPayment(CoffeeOrder coffeeOrder) {
        Scanner scanner = ApplicationContextFactory.getInstance().getBean("scanner", Scanner.class);
        System.out.println("Now please introduce your card details: \n");
        System.out.println("Introduce card number: ");
        String cardNumber = scanner.nextLine();
        System.out.println("Introduce the name on the card: ");
        String cardOwner = scanner.nextLine();
        System.out.println("Introduce expiry date of card: ");
        String dateOfExpiry = scanner.nextLine();
        System.out.println("Introduce CIV: ");
        int civ = NumberGenerator.generateInteger();
        Card card = new Card(cardNumber, cardOwner, dateOfExpiry, civ);
        Map<String, Integer> orderedCoffees = getOrderForPost(coffeeOrder);
        Payment payment = new Payment(card, coffeeOrder.getCustomerName(), orderedCoffees);
        payment.setPaymentId(ApplicationContextFactory.getInstance().getBean("idGenerator", IdGenerator.class).generateId());
        RestClient client = ApplicationContextFactory.getInstance().getBean("client", RestClient.class);
        client.create(payment);
    }

    private static Map<String, Integer> getOrderForPost(CoffeeOrder coffeeOrder) {
        Map<String, Integer> createMap = new HashMap<>();
        Map<Coffee, Integer> orderedCoffees = coffeeOrder.getOrderedCoffeesAndQuantity();
        for(Coffee coffee: coffeeOrder.getOrderedCoffeesAndQuantity().keySet()){
            String nameOfCoffee = coffee.getCoffeeName();
            createMap.put(nameOfCoffee, orderedCoffees.get(coffee));
        }
        return createMap;
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
        ApplicationContextFactory.getInstance().getBean("printer", Printer.class).printRegisteringNameMessage();
        String customerName = ApplicationContextFactory.getInstance().getBean("scanner", Scanner.class).nextLine();
        ApplicationContextFactory.getInstance().getBean("printer", Printer.class).printNewLine();
        return customerName;
    }
}

