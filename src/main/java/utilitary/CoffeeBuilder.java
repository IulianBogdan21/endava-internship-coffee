package utilitary;

import domain.BlackCoffeeBasedBeverage;
import domain.Coffee;
import domain.EspressoBasedBeverage;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class CoffeeBuilder {
    private static final int baseTypeLowerLimit = 1;
    private static final int baseTypeHigherLimit = 2;
    private static final int addIngredientLowerLimit = 0;
    private static final int addIngredientHigherLimit = 5;

    public static Coffee buildCustomisableCoffee(@NotNull Scanner scanner, String customerName){
        while(true) {
            System.out.println("\nDesign your own coffee. Let's start with the base");
            System.out.println("What do you want as base? Black coffee or espresso shots?\n1: Espresso shots\n2: Black coffee\n");
            System.out.println("Introduce your option: ");
            int optionRead =
                    OptionsValidGenerator.generateAndValidateIntegerFromCertainInterval(scanner, baseTypeLowerLimit, baseTypeHigherLimit);
            Coffee designedCoffee = null;
            System.out.println("\nIntroduce number of shots: ");
            int numberOfShots = OptionsValidGenerator.generateAndValidateIntegerWithNoIntervalConstraints(scanner);
            String baseCoffeeName = null;
            if (optionRead == 1) {
                designedCoffee = new EspressoBasedBeverage(customerName, numberOfShots);
                baseCoffeeName = "espresso";
            } else if (optionRead == 2) {
                designedCoffee = new BlackCoffeeBasedBeverage(customerName, numberOfShots);
                baseCoffeeName = "black coffee";
            }
            System.out.println("\nYou added " + String.valueOf(numberOfShots) + "X " + baseCoffeeName + " to your coffee base\n");
            while (true) {
                System.out.println("0: Finish coffee\n1: Add milk foam\n2: Add steamed milk\n3: Add cinnamon\n4: Add honey\n5: Add syrup\n");
                System.out.println("Introduce option: ");
                int ingredientOption = OptionsValidGenerator.generateAndValidateIntegerFromCertainInterval(
                        scanner, addIngredientLowerLimit, addIngredientHigherLimit);
                Ingredients chosenIngredient = null;
                String nameOfIngredient = "";
                switch (ingredientOption) {
                    case 1:
                        chosenIngredient = Ingredients.MILK_FOAM;
                        nameOfIngredient = "milk foam";
                        break;
                    case 2:
                        chosenIngredient = Ingredients.STEAMED_MILK;
                        nameOfIngredient = "steamed milk";
                        break;
                    case 3:
                        chosenIngredient = Ingredients.CINNAMON;
                        nameOfIngredient = "cinnamon";
                        break;
                    case 4:
                        chosenIngredient = Ingredients.HONEY;
                        nameOfIngredient = "honey";
                        break;
                    case 5:
                        chosenIngredient = Ingredients.SYRUP;
                        nameOfIngredient = "syrup";
                        break;
                    case 0:
                        return designedCoffee;
                }
                System.out.println("\nIntroduce amount of ingredient(units): ");
                int ingredientAmount = OptionsValidGenerator.generateAndValidateIntegerWithNoIntervalConstraints(scanner);
                if (optionRead == 1) {
                    ((EspressoBasedBeverage) designedCoffee).addIngredientToCoffee(chosenIngredient, ingredientAmount);
                } else if (optionRead == 2) {
                    ((BlackCoffeeBasedBeverage) designedCoffee).addIngredientToCoffee(chosenIngredient, ingredientAmount);
                }
                System.out.println("\nYou have added " + String.valueOf(ingredientAmount) + "X " + nameOfIngredient + " to your coffee");
            }
        }
    }

}