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
}
