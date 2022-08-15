package com.coffeeshop.utilitary.printers;

import com.coffeeshop.models.coffeeRoot.Coffee;
import com.coffeeshop.models.customer.CoffeeOrder;
import com.coffeeshop.models.shop.CoffeeShop;
import com.coffeeshop.models.shop.Ingredients;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Printer {

    public Printer(){}

    public void printCoffeeShopName(CoffeeShop openCoffeeShop){
        System.out.println("----------" + openCoffeeShop.getCoffeeShopName() + "----------" + "\n");
    }

    public void printOrderedCoffeesAndTheirAmount(CoffeeOrder coffeeOrder) {
        System.out.println("You ordered the following:\n");
        for(Coffee coffee: coffeeOrder.getOrderedCoffeesAndQuantity().keySet()){
            System.out.println(coffeeOrder.getOrderedCoffeesAndQuantity().get(coffee) +
                    "X " + coffee.getCoffeeName() + " where 1 " + coffee.getCoffeeName() + " is " +
                    coffee.getPrice() + " euros");
        }
    }

    public void printMenu(CoffeeShop openCoffeeShop){
        System.out.println("0: Finish order\n" + openCoffeeShop.getAllBeverages() + "6: Design your own coffee\n\nIntroduce option: ");
    }

    public void printMenuInvalidOption(){
        System.out.println("You did not introduce a valid option. Try again!\n");
    }

    public void printCostOfOrder(double addedProfit){
        System.out.println("\nTotal cost is: " + addedProfit);
    }

    public void printCurrentProfitOfCoffeeShop(CoffeeShop coffeeShop){
        System.out.println("Shop's current profit is: " + coffeeShop.getProfit() + "\n");
    }

    public void printQuestionHowManyOfTheChosenCoffeeDoesTheCustomerWant(){
        System.out.println("\nHow many of this coffee do you want? ");
    }

    public void printUpdatedOrder(Coffee orderedCoffee, int amountOfCoffee){
        System.out.println("\nYou added to the order " + amountOfCoffee + " " + orderedCoffee.getCoffeeName());
    }

    public void printOptionsForOrderStatus(){
        System.out.println("Do you want to drink the coffee here or somewhere else?\n1: Pickup\n2: Delivery\n\nIntroduce option: ");
    }

    public void printAdditionalInformationAboutTheMenu(){
        System.out.println("\n");
        System.out.println("Select the coffees you want. Introduce the number for the coffees you want and then the quantity. " +
                "When your order is done, introduce 0" + "\n");
    }

    public void printBaseOptionsForCustomisableCoffee(){
        System.out.println("\nDesign your own coffee. Let's start with the base");
        System.out.println("What do you want as base? Black coffee or espresso shots?\n1: Espresso shots\n2: Black coffee\n");
        System.out.println("Introduce your option: ");
    }

    public void printAskingForNumberOfShots(){
        System.out.println("\nIntroduce number of shots: ");
    }

    public void printConfirmationOfAddedBase(String baseCoffeeName, int numberOfShots){
        System.out.println("\nYou added " + numberOfShots + "X " + baseCoffeeName + " to your coffee base\n");
    }

    public void printMenuForCustomisableCoffee(){
        System.out.println("0: Finish coffee\n1: Add milk foam\n2: Add steamed milk\n3: Add cinnamon\n4: Add honey\n5: Add syrup\n");
        System.out.println("Introduce option: ");
    }

    public void printMessageAskingForAmountOfIngredient(){
        System.out.println("\nIntroduce amount of ingredient(units): ");
    }

    public void printConfirmationOfAddedIngredient(String nameOfIngredient, int ingredientAmount){
        System.out.println("\nYou have added " + ingredientAmount + "X " + nameOfIngredient + " to your coffee");
    }

    public void printRegisteringNameMessage(){
        System.out.println("Introduce your name: ");
    }

    public void printNewLine(){
        System.out.println("\n");
    }

    public void printNameOfIngredientAndQuantity(Map<Ingredients, Integer> stock, Ingredients iteratedIngredient,
                                                        Map<Ingredients, String> nameOfIngredients){
        System.out.println(stock.get(iteratedIngredient) + "X " + nameOfIngredients.get(iteratedIngredient));
    }

    public void printLines() {
        System.out.println("----------");
    }

    public void printWarningMessage(){
        System.out.println("WARNING! THE SHOP IS LOW ON SOME SUPPLIES");
    }

    public void printStockMessage(){
        System.out.println("Current stock:");
    }

    public void printInvalidNumberMessage(){
        System.out.println("Invalid option! You have not introduced a number!");
    }

    public void printNumberNotWithinInterval(){
        System.out.println("You need to introduce an option within the interval!");
    }

    public void printCardIntroduction(){
        System.out.println("Now please introduce your card details: \n");
    }

    public void printIntroduceCard(){
        System.out.println("Introduce card number: ");
    }

    public void printIntroduceCardHolder(){
        System.out.println("Introduce the name on the card: ");
    }

    public void printIntroduceCardExpiryDate(){
        System.out.println("Introduce expiry date of card: ");
    }

    public void printIntroduceCardCiv(){
        System.out.println("Introduce CIV: ");
    }

    public void printExceptionMessage(String message){
        System.out.println(message);
    }
}
