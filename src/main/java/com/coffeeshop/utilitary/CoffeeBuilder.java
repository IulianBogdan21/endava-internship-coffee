package com.coffeeshop.utilitary;

import com.coffeeshop.domain.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CoffeeBuilder {
    private static final int BASE_TYPE_LOWER_LIMIT = 1;
    private static final int BASE_TYPE_HIGHER_LIMIT = 2;
    private static final int ADD_INGREDIENT_LOWER_LIMIT = 0;
    private static final int ADD_INGREDIENT_HIGHER_LIMIT = 5;
    private static final int FINISH_CUSTOMISABLE_COFFEE = 0;
    private static final int ESPRESSO_BASE = 1;
    private static final int BLACK_COFFEE_BASE = 2;

    /**
     * @param scanner Scanner
     * @return a customised coffee , designed by the customer
     */
    public static Coffee buildCustomisableCoffee(@NotNull Scanner scanner){
        Map<Ingredients, Integer> coffeeIngredients = new HashMap<>();
        MessagePrinter.printBaseOptionsForCustomisableCoffee();
        int baseOption =
                NumberGenerator.generateAndValidateIntegerFromCertainInterval(scanner, BASE_TYPE_LOWER_LIMIT, BASE_TYPE_HIGHER_LIMIT);
        int numberOfShots = getNumberOfShots(scanner);
        coffeeIngredients.put(getCoffeeBase(baseOption), numberOfShots);
        String baseCoffeeName = getCoffeeBaseName(baseOption);
        MessagePrinter.printConfirmationOfAddedBase(baseCoffeeName, numberOfShots);
        return addOptionalIngredientsToCustomisedCoffee(scanner, coffeeIngredients);
    }

    /**
     * @param menuOption integer that represents an option from the menu
     * @param customerName - the name of the customer - String
     * @param scanner Scanner
     * @return a new Coffee corresponding to the chosen menu option
     */
    public static Coffee buildCoffeeFromMenu(int menuOption, String customerName, Scanner scanner){
        Coffee customisedCoffee;
        switch (menuOption) {
            case 1:
                customisedCoffee = new Espresso(customerName);
                break;
            case 2:
                customisedCoffee = new Machiatto(customerName);
                break;
            case 3:
                customisedCoffee = new CoffeeLatte(customerName);
                break;
            case 4:
                customisedCoffee = new Cappucino(customerName);
                break;
            case 5:
                customisedCoffee = new CoffeeMiel(customerName);
                break;
            case 6:
                customisedCoffee = CoffeeBuilder.buildCustomisableCoffee(scanner);
                customisedCoffee.setCustomerName(customerName);
            default:
                customisedCoffee = null;
        };
        return customisedCoffee;
    }

    /**
     * @param scanner Scanner
     * @return integer - number of shots of a certain coffee
     */
    private static int getNumberOfShots(Scanner scanner){
        MessagePrinter.printAskingForNumberOfShots();
        return NumberGenerator.generateAndValidateIntegerWithNoIntervalConstraints(scanner);
    }

    /**
     * @param baseOption integer - option for the coffee base - can be 1 or 2
     * @return String - espresso if option 1, black coffee if 2
     */
    private static String getCoffeeBaseName(int baseOption){
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
    private static Ingredients getCoffeeBase(int baseOption){
        if (baseOption == 1) {
            return Ingredients.ESPRESSO;
        } else if (baseOption == 2) {
            return Ingredients.BLACK_COFFEE;
        }
        return null;
    }

    /**
     * @param ingredientOption - integer corresponding to an ingredient from the menu of all ingredients
     * @return Ingredients enum - the ingredient corresponding to the chosen option
     */
    @Contract(pure = true)
    private static @Nullable Ingredients getIngredientAccordingToChosenOption(int ingredientOption){
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
    private static @Nullable String getIngredientNameAccordingToChosenOption(int ingredientOption){
        String ingredientName = "";
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
     * @param scanner Scanner
     * @return integer - how much of chosen ingredient to put in coffee
     */
    private static int getAmountOfIngredient(Scanner scanner){
        MessagePrinter.printMessageAskingForAmountOfIngredient();
        return NumberGenerator.generateAndValidateIntegerWithNoIntervalConstraints(scanner);
    }

    /**
     * @param scanner Scanner
     * @return the final customised coffee after adding all the ingredients the customer wants
     */
    private static Coffee addOptionalIngredientsToCustomisedCoffee(Scanner scanner, Map<Ingredients, Integer> coffeeIngredients){
        while(true){
            MessagePrinter.printMenuForCustomisableCoffee();
            int ingredientOption = NumberGenerator.generateAndValidateIntegerFromCertainInterval(
                    scanner, ADD_INGREDIENT_LOWER_LIMIT, ADD_INGREDIENT_HIGHER_LIMIT);
            if(ingredientOption == FINISH_CUSTOMISABLE_COFFEE)
                return makeCoffeeFromIngredients(coffeeIngredients);
            Ingredients chosenIngredient = getIngredientAccordingToChosenOption(ingredientOption);
            String nameOfIngredient = getIngredientNameAccordingToChosenOption(ingredientOption);
            int ingredientAmount = getAmountOfIngredient(scanner);
            coffeeIngredients.put(chosenIngredient, ingredientAmount);
            MessagePrinter.printConfirmationOfAddedIngredient(nameOfIngredient, ingredientAmount);
        }
    }

    public static Coffee makeCoffeeFromIngredients(Map<Ingredients, Integer> ingredients){
        Integer amountOfEspresso = ingredients.get(Ingredients.ESPRESSO);
        Integer amountOfBlackCoffee = ingredients.get(Ingredients.BLACK_COFFEE);
        CoffeeBase newCoffee;
        if(amountOfEspresso == null){
            newCoffee = new BlackCoffeeBasedBeverage(amountOfBlackCoffee);
            ingredients.remove(Ingredients.BLACK_COFFEE);
        }
        else{
            newCoffee = new EspressoBasedBeverage(amountOfEspresso);
            ingredients.remove(Ingredients.ESPRESSO);
        }
        for(Ingredients currentIngredient: ingredients.keySet()){
            newCoffee.addIngredient(currentIngredient, ingredients.get(currentIngredient));
        }
        return newCoffee;
    }

}
