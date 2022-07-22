package utilitary;

import domain.BlackCoffeeBasedBeverage;
import domain.Coffee;
import domain.EspressoBasedBeverage;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class CoffeeBuilder {

    public static Coffee buildCustomisableCoffee(@NotNull Scanner scanner, String customerName){
        while(true) {
            System.out.println("\nDesign your own coffee. Let's start with the base");
            System.out.println("What do you want as base? Black coffee or espresso shots?\n1: Espresso shots\n2: Black coffee\n");
            System.out.println("Introduce your option: ");
            int optionRead;
            try {
                optionRead = scanner.nextInt();
                scanner.nextLine();
            }
            catch (Exception ex){
                System.out.println("Invalid option! Please try again!");
                scanner.next();
                continue;
            }
            Coffee designedCoffee = null;
            System.out.println("\nIntroduce number of shots: ");
            int numberOfShots;
            try {
                numberOfShots = scanner.nextInt();
            }
            catch (Exception ex){
                System.out.println("Invalid option! Please try again");
                scanner.next();
                continue;
            }
            String baseCoffeeName = null;
            scanner.nextLine();
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
                int ingredientOption;
                try {
                    ingredientOption = scanner.nextInt();
                    scanner.nextLine();
                }
                catch (Exception ex){
                    System.out.println("Invalid option! Please try again");
                    scanner.next();
                    continue;
                }
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
                int ingredientAmount;
                try {
                    ingredientAmount = scanner.nextInt();
                    scanner.nextLine();
                }
                catch (Exception ex){
                    System.out.println("Invalid option! Please try again");
                    scanner.next();
                    continue;
                }
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
