package utilitary;

import java.util.Scanner;

/**
 * class with static methods that generate valid integers for the main menu (integers between certain intervals or just simple integers)
 */
public class NumberGenerator {

    /**
     * @param scanner Scanner
     * @return Integer with value 1 or 2
     */
    public static int generateAndValidateIntegerFromCertainInterval(Scanner scanner, int firstOption, int lastOption){
        while (true){
            int optionRead;
            try {
                optionRead = scanner.nextInt();
                scanner.nextLine();
            }
            catch (Exception ex){
                System.out.println("Invalid option! You have not introduced a number!");
                scanner.next();
                continue;
            }
            if(optionRead < firstOption || optionRead > lastOption){
                System.out.println("You need to introduce 1 or 2 depending on your option!");
                continue;
            }
            return optionRead;
        }
    }

    /**
     * @param scanner Scanner
     * @return Integer with no value constraints
     */
    public static int generateAndValidateIntegerWithNoIntervalConstraints(Scanner scanner){
        while (true){
            try {
                int optionRead = scanner.nextInt();
                scanner.nextLine();
                return optionRead;
            }
            catch (Exception ex){
                System.out.println("Invalid option! You have not introduced a number!");
                scanner.next();
            }
        }
    }
}
