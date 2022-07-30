package utilitary;

import domain.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
     * @param customerName - String = name of customer
     * @return a customised coffee , designed by the customer
     */
    public static Coffee buildCustomisableCoffee(@NotNull Scanner scanner, String customerName){
        MessagePrinter.printBaseOptionsForCustomisableCoffee();
        int baseOption =
                NumberGenerator.generateAndValidateIntegerFromCertainInterval(scanner, BASE_TYPE_LOWER_LIMIT, BASE_TYPE_HIGHER_LIMIT);
        int numberOfShots = getNumberOfShots(scanner);
        Coffee designedCoffee = getCoffeeBase(baseOption, customerName, numberOfShots);
        String baseCoffeeName = getCoffeeBaseName(baseOption);
        MessagePrinter.printConfirmationOfAddedBase(baseCoffeeName, numberOfShots);
        return addOptionalIngredientsToCustomisedCoffee(designedCoffee, scanner, baseOption);
    }

    /**
     * @param menuOption integer that represents an option from the menu
     * @param customerName - the name of the customer - String
     * @param scanner Scanner
     * @return a new Coffee corresponding to the chosen menu option
     */
    public static Coffee buildCoffeeFromMenu(int menuOption, String customerName, Scanner scanner){
        return switch (menuOption) {
            case 1 -> new Espresso(customerName);
            case 2 -> new Machiatto(customerName);
            case 3 -> new CoffeeLatte(customerName);
            case 4 -> new Cappucino(customerName);
            case 5 -> new CoffeeMiel(customerName);
            case 6 -> CoffeeBuilder.buildCustomisableCoffee(scanner, customerName);
            default -> null;
        };
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
        if (baseOption == 1) {
            return "espresso";
        }else if(baseOption == 2) {
            return "black coffee";
        }
        return null;
    }

    /**
     * @param baseOption - integer - option of a base coffee menu
     * @param customerName - String - name of customer
     * @param numberOfShots - integer - number of shots of a certain coffee
     * @return - the designed coffee after adding the base
     */
    private static Coffee getCoffeeBase(int baseOption, String customerName, int numberOfShots){
        if (baseOption == 1) {
            return new EspressoBasedBeverage(customerName, numberOfShots);
        } else if (baseOption == 2) {
            return new BlackCoffeeBasedBeverage(customerName, numberOfShots);
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
     * @param designedCoffee Coffee - the coffee that the customer creates
     * @param scanner Scanner
     * @param baseOption integer - menu option for choosing the base for the customisable coffee
     * @return the final customised coffee after adding all the ingredients the customer wants
     */
    @Contract("_, _, _ -> param1")
    private static Coffee addOptionalIngredientsToCustomisedCoffee(Coffee designedCoffee, Scanner scanner, int baseOption){
        while(true){
            MessagePrinter.printMenuForCustomisableCoffee();
            int ingredientOption = NumberGenerator.generateAndValidateIntegerFromCertainInterval(
                    scanner, ADD_INGREDIENT_LOWER_LIMIT, ADD_INGREDIENT_HIGHER_LIMIT);
            if(ingredientOption == FINISH_CUSTOMISABLE_COFFEE)
                return designedCoffee;
            Ingredients chosenIngredient = getIngredientAccordingToChosenOption(ingredientOption);
            String nameOfIngredient = getIngredientNameAccordingToChosenOption(ingredientOption);
            int ingredientAmount = getAmountOfIngredient(scanner);
            if (baseOption == ESPRESSO_BASE) {
                ((EspressoBasedBeverage) designedCoffee).addIngredientToCoffee(chosenIngredient, ingredientAmount);
            } else if (baseOption == BLACK_COFFEE_BASE) {
                ((BlackCoffeeBasedBeverage) designedCoffee).addIngredientToCoffee(chosenIngredient, ingredientAmount);
            }
            MessagePrinter.printConfirmationOfAddedIngredient(nameOfIngredient, ingredientAmount);
        }
    }

}
