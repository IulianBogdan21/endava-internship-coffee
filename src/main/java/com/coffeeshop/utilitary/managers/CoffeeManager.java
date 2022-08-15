package com.coffeeshop.utilitary.managers;

import com.coffeeshop.models.coffeeRoot.Coffee;
import com.coffeeshop.models.customer.CoffeeOrder;
import com.coffeeshop.models.customer.OrderStatus;
import com.coffeeshop.models.defaultCoffees.*;
import com.coffeeshop.service.implementations.CoffeeMakerService;
import com.coffeeshop.models.shop.Ingredients;
import com.coffeeshop.utilitary.factories.ApplicationContextFactory;
import com.coffeeshop.utilitary.generators.NumberGenerator;
import com.coffeeshop.utilitary.printers.Printer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("coffeeManager")
@DependsOn(value = {"printer", "numberGenerator", "coffeeMakerService"})
public class CoffeeManager {
    private static final int BASE_TYPE_LOWER_LIMIT = 1;
    private static final int BASE_TYPE_HIGHER_LIMIT = 2;
    private static final int ADD_INGREDIENT_LOWER_LIMIT = 0;
    private static final int ADD_INGREDIENT_HIGHER_LIMIT = 5;
    private static final int FINISH_CUSTOMISABLE_COFFEE = 0;
    private static final int ESPRESSO_BASE = 1;
    private static final int BLACK_COFFEE_BASE = 2;

    private Printer printer;
    private NumberGenerator numberGenerator;
    private CoffeeMakerService coffeeMakerService;

    public CoffeeManager(){}

    @Autowired
    public void setCoffeeMaker(CoffeeMakerService coffeeMakerService){
        this.coffeeMakerService = coffeeMakerService;
    }

    @Autowired
    public void setPrinter(Printer printer){
        this.printer = printer;
    }

    @Autowired
    public void setNumberGenerator(NumberGenerator numberGenerator){
        this.numberGenerator = numberGenerator;
    }

    /**
     * @return a customised coffee , designed by the customer
     */
    public Coffee buildCustomisableCoffee(){
        Map<Ingredients, Integer> coffeeIngredients = new HashMap<>();
        printer.printBaseOptionsForCustomisableCoffee();
        int baseOption = numberGenerator.generateIntegerWithinInterval(BASE_TYPE_LOWER_LIMIT, BASE_TYPE_HIGHER_LIMIT);
        int numberOfShots = ApplicationContextFactory.getInstance().getBean("consoleManager", ConsoleManager.class).getNumberOfShots();
        coffeeIngredients.put(getCoffeeBase(baseOption), numberOfShots);
        String baseCoffeeName = getCoffeeBaseName(baseOption);
        printer.printConfirmationOfAddedBase(baseCoffeeName, numberOfShots);
        return addOptionalIngredientsToCustomisedCoffee(coffeeIngredients);
    }

    /**
     * @param menuOption integer that represents an option from the menu
     * @param customerName - the name of the customer - String
     * @return a new Coffee corresponding to the chosen menu option
     */
    public Coffee buildCoffeeFromMenu(int menuOption, String customerName){
        Coffee customisedCoffee;
        switch (menuOption) {
            case 1 -> customisedCoffee = new Espresso(customerName);
            case 2 -> customisedCoffee = new Machiatto(customerName);
            case 3 -> customisedCoffee = new CoffeeLatte(customerName);
            case 4 -> customisedCoffee = new Cappucino(customerName);
            case 5 -> customisedCoffee = new CoffeeMiel(customerName);
            case 6 -> {
                customisedCoffee = buildCustomisableCoffee();
                customisedCoffee.setCustomerName(customerName);
            }
            default -> customisedCoffee = null;
        }
        return customisedCoffee;
    }

    /**
     * @param baseOption integer - option for the coffee base - can be 1 or 2
     * @return String - espresso if option 1, black coffee if 2
     */
    private String getCoffeeBaseName(int baseOption){
        if (baseOption == ESPRESSO_BASE) {
            return "espresso";
        }else if(baseOption == BLACK_COFFEE_BASE) {
            return "black coffee";
        }
        return null;
    }

    /**
     * returns base ingredient corresponding to chosen option
     * @param baseOption - integer - option of a base coffee menu
     */
    private Ingredients getCoffeeBase(int baseOption){
        if (baseOption == ESPRESSO_BASE) {
            return Ingredients.ESPRESSO;
        } else if (baseOption == BLACK_COFFEE_BASE) {
            return Ingredients.BLACK_COFFEE;
        }
        return null;
    }

    /**
     * @param statusOption Integer equal to 1 or 2
     * @return PICKUP OrderStatus if integer is 1, DELIVERY OrderStatus if integer is 2
     */
    @Nullable
    public OrderStatus getStatusBasedOnChosenOption(int statusOption) {
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
     * @param ingredientOption - integer corresponding to an ingredient from the menu of all ingredients
     * @return Ingredients enum - the ingredient corresponding to the chosen option
     */
    @Contract(pure = true)
    private @Nullable Ingredients getIngredientAccordingToChosenOption(int ingredientOption){
        Ingredients chosenIngredient;
        switch(ingredientOption){
            case 1:
                chosenIngredient = Ingredients.MILK_FOAM;
                break;
            case 2:
                chosenIngredient = Ingredients.STEAMED_MILK;
                break;
            case 3:
                chosenIngredient = Ingredients.CINNAMON;
                break;
            case 4:
                chosenIngredient = Ingredients.HONEY;
                break;
            case 5:
                chosenIngredient = Ingredients.SYRUP;
                break;
            default:
                return null;
        }
        return chosenIngredient;
    }

    /**
     * @param ingredientOption - integer corresponding to an ingredient from the menu of all ingredients
     * @return String - the name of the ingredient corresponding to the chosen option from menu
     */
    @Contract(pure = true)
    private @Nullable String getIngredientNameAccordingToChosenOption(int ingredientOption){
        String ingredientName;
        switch(ingredientOption){
            case 1:
                ingredientName = "milk foam";
                break;
            case 2:
                ingredientName = "steamed milk";
                break;
            case 3:
                ingredientName = "cinnamon";
                break;
            case 4:
                ingredientName = "honey";
                break;
            case 5:
                ingredientName = "syrup";
                break;
            default:
                return null;
        }
        return ingredientName;
    }

    /**
     * @return the final customised coffee after adding all the ingredients the customer wants
     */
    private Coffee addOptionalIngredientsToCustomisedCoffee(Map<Ingredients, Integer> coffeeIngredients){
        while(true){
            printer.printMenuForCustomisableCoffee();
            int ingredientOption = numberGenerator.generateIntegerWithinInterval(ADD_INGREDIENT_LOWER_LIMIT, ADD_INGREDIENT_HIGHER_LIMIT);
            if(ingredientOption == FINISH_CUSTOMISABLE_COFFEE)
                return coffeeMakerService.brewCoffeeAfterRecipe(coffeeIngredients);
            Ingredients chosenIngredient = getIngredientAccordingToChosenOption(ingredientOption);
            String nameOfIngredient = getIngredientNameAccordingToChosenOption(ingredientOption);
            int ingredientAmount = ApplicationContextFactory.getInstance().getBean("consoleManager", ConsoleManager.class)
                    .getAmountOfIngredient();
            coffeeIngredients.put(chosenIngredient, ingredientAmount);
            printer.printConfirmationOfAddedIngredient(nameOfIngredient, ingredientAmount);
        }
    }

    /**
     * @param coffeeOrder - customer's order
     * @return a map where key entities are replaced with their name (String)
     */
    public Map<String, Integer> getIngredientsAndAmountFromOrder(CoffeeOrder coffeeOrder) {
        Map<String, Integer> coffeesAndQuantityFromOrderWithStringKeys = new HashMap<>();
        Map<Coffee, Integer> orderedCoffees = coffeeOrder.getOrderedCoffeesAndQuantity();
        for(Coffee coffee: coffeeOrder.getOrderedCoffeesAndQuantity().keySet()){
            String nameOfCoffee = coffee.getCoffeeName();
            coffeesAndQuantityFromOrderWithStringKeys.put(nameOfCoffee, orderedCoffees.get(coffee));
        }
        return coffeesAndQuantityFromOrderWithStringKeys;
    }

}
