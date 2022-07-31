package com.coffeeshop.utilitary;

import com.coffeeshop.domain.Coffee;
import com.coffeeshop.domain.CoffeeOrder;
import com.coffeeshop.domain.CoffeeShop;

import java.util.Map;

public class MessagePrinter {

    public static void printCoffeeShopName(CoffeeShop openCoffeeShop){
        System.out.println("----------" + openCoffeeShop.getCoffeeShopName() + "----------" + "\n");
    }

    public static void printOrderedCoffeesAndTheirAmount(Map<Ingredients, Double> pricesForEachIngredient, CoffeeOrder coffeeOrder) {
        System.out.println("You ordered the following:\n");
        for(Coffee coffee: coffeeOrder.getOrderedCoffeesAndQuantity().keySet()){
            System.out.println(coffeeOrder.getOrderedCoffeesAndQuantity().get(coffee) +
                    "X " + coffee.getCoffeeName() + " where 1 " + coffee.getCoffeeName() + " is " +
                    coffee.getPrice(pricesForEachIngredient) + " euros");
        }
    }

    public static void printMenu(CoffeeShop openCoffeeShop){
        System.out.println("0: Finish order\n" + openCoffeeShop.getAllBeverages() + "6: Design your own coffee\n\nIntroduce option: ");
    }

    public static void printMenuInvalidOption(){
        System.out.println("You did not introduce a valid option. Try again!\n");
    }

    public static void printCostOfOrder(double addedProfit){
        System.out.println("\nTotal cost is: " + addedProfit);
    }

    public static void printCurrentProfitOfCoffeeShop(CoffeeShop coffeeShop){
        System.out.println("Shop's current profit is: " + coffeeShop.getProfit() + "\n");
    }

    public static void printQuestionHowManyOfTheChosenCoffeeDoesTheCustomerWant(){
        System.out.println("\nHow many of this coffee do you want? ");
    }

    public static void printUpdatedOrder(Coffee orderedCoffee, int amountOfCoffee){
        System.out.println("\nYou added to the order " + amountOfCoffee + " " + orderedCoffee.getCoffeeName());
    }

    public static void printOptionsForOrderStatus(){
        System.out.println("Do you want to drink the coffee here or somewhere else?\n1: Pickup\n2: Delivery\n\nIntroduce option: ");
    }

    public static void printAdditionalInformationAboutTheMenu(){
        System.out.println("\n");
        System.out.println("Select the coffees you want. Introduce the number for the coffees you want and then the quantity. " +
                "When your order is done, introduce 0" + "\n");
    }

    public static void printBaseOptionsForCustomisableCoffee(){
        System.out.println("\nDesign your own coffee. Let's start with the base");
        System.out.println("What do you want as base? Black coffee or espresso shots?\n1: Espresso shots\n2: Black coffee\n");
        System.out.println("Introduce your option: ");
    }

    public static void printAskingForNumberOfShots(){
        System.out.println("\nIntroduce number of shots: ");
    }

    public static void printConfirmationOfAddedBase(String baseCoffeeName, int numberOfShots){
        System.out.println("\nYou added " + numberOfShots + "X " + baseCoffeeName + " to your coffee base\n");
    }

    public static void printMenuForCustomisableCoffee(){
        System.out.println("0: Finish coffee\n1: Add milk foam\n2: Add steamed milk\n3: Add cinnamon\n4: Add honey\n5: Add syrup\n");
        System.out.println("Introduce option: ");
    }

    public static void printMessageAskingForAmountOfIngredient(){
        System.out.println("\nIntroduce amount of ingredient(units): ");
    }

    public static void printConfirmationOfAddedIngredient(String nameOfIngredient, int ingredientAmount){
        System.out.println("\nYou have added " + ingredientAmount + "X " + nameOfIngredient + " to your coffee");
    }

    public static void printRegisteringNameMessage(){
        System.out.println("Introduce your name: ");
    }

    public static void printNewLine(){
        System.out.println("\n");
    }

    public static void printNameOfIngredientAndQuantity(Map<Ingredients, Integer> stock, Ingredients iteratedIngredient,
                                                        Map<Ingredients, String> nameOfIngredients){
        System.out.println(stock.get(iteratedIngredient) + "X " + nameOfIngredients.get(iteratedIngredient));
    }

    public static void printLines() {
        System.out.println("----------");
    }
}
