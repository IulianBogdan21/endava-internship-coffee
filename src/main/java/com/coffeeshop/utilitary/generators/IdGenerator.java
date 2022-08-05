package com.coffeeshop.utilitary.generators;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * class used to generate id's for client orders
 */
@Component
public class IdGenerator{
    private final List<Integer> listOfPossibleIds;
    private final Random randomizer;
    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 10000;
    private static final int AMOUNT_OF_IDS = 1000;

    public IdGenerator(){
        randomizer = new Random();
        this.listOfPossibleIds = generateListOfDistinctIds();
    }

     /**
     * @return random integer from the list of integers that has been previously constructed
     * chosen integer is also removed from the list of integers, so it cannot be chosen again
     */
    public Integer generateId(){
        int randomPositionInArray = randomizer.nextInt(listOfPossibleIds.size());
        return listOfPossibleIds.remove(randomPositionInArray);
    }

     /**
     * @return A list of distinct random integers within a specified interval
     */
    private List<Integer> generateListOfDistinctIds(){
        return randomizer.ints(MINIMUM_NUMBER, MAXIMUM_NUMBER).distinct().limit(AMOUNT_OF_IDS)
                .boxed().collect(Collectors.toList());
    }
}
