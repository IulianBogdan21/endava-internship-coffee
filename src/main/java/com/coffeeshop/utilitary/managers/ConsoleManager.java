package com.coffeeshop.utilitary.managers;

import com.coffeeshop.utilitary.factories.ApplicationContextFactory;
import com.coffeeshop.utilitary.generators.NumberGenerator;
import com.coffeeshop.utilitary.printers.Printer;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleManager {
    public ConsoleManager(){}

    public String getCardNumber(){
        ApplicationContextFactory.getInstance().getBean("printer", Printer.class).printIntroduceCard();
        return ApplicationContextFactory.getInstance().getBean("scanner", Scanner.class).nextLine();
    }

    public String getCardOwner(){
        ApplicationContextFactory.getInstance().getBean("printer", Printer.class).printIntroduceCardHolder();
        return ApplicationContextFactory.getInstance().getBean("scanner", Scanner.class).nextLine();
    }

    public String getDateOfExpiry(){
        ApplicationContextFactory.getInstance().getBean("printer", Printer.class).printIntroduceCardExpiryDate();
        return ApplicationContextFactory.getInstance().getBean("scanner", Scanner.class).nextLine();
    }

    public Integer getCiv(){
        ApplicationContextFactory.getInstance().getBean("printer", Printer.class).printIntroduceCardCiv();
        return ApplicationContextFactory.getInstance().getBean("numberGenerator", NumberGenerator.class).generateInteger();
    }

    /**
     * @return integer - how much of chosen ingredient to put in coffee
     */
    public Integer getAmountOfIngredient(){
        ApplicationContextFactory.getInstance().getBean("printer", Printer.class).printMessageAskingForAmountOfIngredient();
        return ApplicationContextFactory.getInstance().getBean("numberGenerator", NumberGenerator.class).generateInteger();
    }

    /**
     * @return integer - number of shots of a certain coffee
     */
    public Integer getNumberOfShots(){
        ApplicationContextFactory.getInstance().getBean("printer", Printer.class).printAskingForNumberOfShots();
        return ApplicationContextFactory.getInstance().getBean("numberGenerator", NumberGenerator.class).generateInteger();
    }
}
