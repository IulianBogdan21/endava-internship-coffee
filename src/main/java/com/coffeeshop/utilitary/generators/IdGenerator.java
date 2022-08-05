package com.coffeeshop.utilitary.generators;

import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * class used to generate id's for client orders
 */
@ComponentScan("idGenerator")
public class IdGenerator{
    private List<Integer> listOfPossibleIds;
    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 10000;
    private static final int AMOUNT_OF_IDS = 1000;
    private Random randomizer;

    public IdGenerator(){}

    /**
     * initialize a large array from where we will get random integers
     */
    @PostConstruct
    private void initializeIdGenerator(){
        randomizer = new Random();
        listOfPossibleIds = randomizer.ints(MINIMUM_NUMBER, MAXIMUM_NUMBER).distinct().limit(AMOUNT_OF_IDS)
                .boxed().collect(Collectors.toList());
    }


    /**
     * @return random integer from the list of integers that has been previously constructed
     */
    public Integer generateId(){
        int randomPositionInArray = randomizer.nextInt(listOfPossibleIds.size());
        return listOfPossibleIds.remove(randomPositionInArray);
    }
}
