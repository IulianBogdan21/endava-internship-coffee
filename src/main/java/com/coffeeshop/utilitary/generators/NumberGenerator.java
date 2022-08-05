package com.coffeeshop.utilitary.generators;

import com.coffeeshop.utilitary.factories.ApplicationContextFactory;
import com.coffeeshop.utilitary.printers.Printer;

import java.util.Scanner;

/**
 * class with static methods that generate valid integers for the main menu (integers between certain intervals or just simple integers)
 */
public class NumberGenerator {

    /**
     * @param firstOption - Integer
     * @param lastOption - Integer
     * @return Integer with value between firstOption and lastOption
     */
    public static int generateIntegerWithinInterval(int firstOption, int lastOption){
        while (true){
            Scanner scanner = ApplicationContextFactory.getInstance().getBean("scanner", Scanner.class);
            int optionRead;
            try {
                optionRead = scanner.nextInt();
                scanner.nextLine();
            }
            catch (Exception ex){
                ApplicationContextFactory.getInstance().getBean("printer", Printer.class).printInvalidNumberMessage();
                scanner.next();
                continue;
            }
            if(optionRead < firstOption || optionRead > lastOption){
                ApplicationContextFactory.getInstance().getBean("printer", Printer.class).printNumberNotWithinInterval();
                continue;
            }
            return optionRead;
        }
    }

    /**
     * @return Integer with no value constraints
     */
    public static int generateInteger(){
        Scanner scanner = ApplicationContextFactory.getInstance().getBean("scanner", Scanner.class);
        while (true){
            try {
                int optionRead = scanner.nextInt();
                scanner.nextLine();
                return optionRead;
            }
            catch (Exception ex){
                ApplicationContextFactory.getInstance().getBean("printer", Printer.class).printInvalidNumberMessage();
                scanner.next();
            }
        }
    }
}
