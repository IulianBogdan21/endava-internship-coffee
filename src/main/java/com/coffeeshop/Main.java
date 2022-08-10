package com.coffeeshop;

import com.coffeeshop.exceptions.CardException;
import com.coffeeshop.models.coffeeRoot.Coffee;
import com.coffeeshop.models.customer.Card;
import com.coffeeshop.models.customer.CoffeeOrder;
import com.coffeeshop.models.customer.OrderStatus;
import com.coffeeshop.models.customer.Payment;
import com.coffeeshop.models.shop.CoffeeShop;
import com.coffeeshop.rest.RestClient;
import com.coffeeshop.service.implementations.CardValidationService;
import com.coffeeshop.service.implementations.IngredientsService;
import com.coffeeshop.utilitary.factories.ApplicationContextFactory;
import com.coffeeshop.utilitary.generators.IdGenerator;
import com.coffeeshop.utilitary.generators.NumberGenerator;
import com.coffeeshop.utilitary.managers.CoffeeManager;
import com.coffeeshop.utilitary.managers.ConsoleManager;
import com.coffeeshop.utilitary.managers.StockManager;
import com.coffeeshop.utilitary.printers.Printer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    private static final int DELIVERY_STATUS_LOWER_LIMIT = 1;
    private static final int DELIVERY_STATUS_HIGHER_LIMIT = 2;
    private static final int COFFEE_TYPE_LOWER_LIMIT = 0;
    private static final int COFFEE_TYPE_HIGHER_LIMIT = 6;
    private static final int FINISH_ORDER = 0;

    public static void main(String[] args) throws Exception {
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
            String customerName = ApplicationContextFactory.getInstance().getBean("consoleManager", ConsoleManager.class)
                    .registerCustomerName();
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
        int statusOption = ApplicationContextFactory.getInstance().getBean("numberGenerator", NumberGenerator.class)
                .generateIntegerWithinInterval(DELIVERY_STATUS_LOWER_LIMIT, DELIVERY_STATUS_HIGHER_LIMIT);
        OrderStatus orderStatus = ApplicationContextFactory.getInstance().getBean("coffeeManager", CoffeeManager.class)
                .getStatusBasedOnChosenOption(statusOption);
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
            int menuOption = ApplicationContextFactory.getInstance().getBean("numberGenerator", NumberGenerator.class)
                    .generateIntegerWithinInterval(COFFEE_TYPE_LOWER_LIMIT, COFFEE_TYPE_HIGHER_LIMIT);
            Coffee orderedCoffee = ApplicationContextFactory.getInstance().getBean("coffeeManager", CoffeeManager.class)
                    .buildCoffeeFromMenu(menuOption, customerName);
            if (menuOption == FINISH_ORDER) {
                try {
                    proceedWithPayment(coffeeOrder);
                    finishCustomerOrder(coffeeShop, coffeeOrder);
                    return;
                }
                catch (CardException ex){
                    ApplicationContextFactory.getInstance().getBean("printer", Printer.class).printExceptionMessage(ex.getMessage());
                    return;
                }
            }
            if (optionChosenFromMenuIsInvalid(orderedCoffee)) {
                ApplicationContextFactory.getInstance().getBean("printer", Printer.class).printMenuInvalidOption();
                continue;
            }
            int amountOfCoffee = ApplicationContextFactory.getInstance().getBean("consoleManager", ConsoleManager.class)
                    .getAmountOfOrderedCoffee();
            updateCoffeeOrder(coffeeOrder, orderedCoffee, amountOfCoffee);
            ApplicationContextFactory.getInstance().getBean("ingredientsService", IngredientsService.class)
                    .updateStock(ApplicationContextFactory.getInstance().getBean("stockManager", StockManager.class)
                            .evaluateAllIngredientsPerCoffeeCommand(orderedCoffee.getIngredientsForCoffeeAndAmount(), amountOfCoffee));
        }
    }

    /**
     * method that deals with the online payment
     * @param coffeeOrder current order of a customer
     */
    private static void proceedWithPayment(CoffeeOrder coffeeOrder) {
        Printer printer = ApplicationContextFactory.getInstance().getBean("printer", Printer.class);
        ConsoleManager consoleManager = ApplicationContextFactory.getInstance().getBean("consoleManager", ConsoleManager.class);
        printer.printCardIntroduction();
        String cardNumber = consoleManager.getCardNumber();
        String cardOwner = consoleManager.getCardOwner();
        String dateOfExpiry = consoleManager.getDateOfExpiry();
        String civ = consoleManager.getCiv();
        Card card = new Card(cardNumber, cardOwner, dateOfExpiry, civ);
        ApplicationContextFactory.getInstance().getBean("cardValidationService", CardValidationService.class).validateCard(card);
        Payment payment = new Payment(card, coffeeOrder.getCustomerName(),
                ApplicationContextFactory.getInstance().getBean("coffeeManager", CoffeeManager.class)
                        .getIngredientsAndAmountFromOrder(coffeeOrder));
        payment.setPaymentId(ApplicationContextFactory.getInstance().getBean("idGenerator", IdGenerator.class).generateId());
        RestClient client = ApplicationContextFactory.getInstance().getBean("client", RestClient.class);
        client.makePayment(payment);
    }
}

