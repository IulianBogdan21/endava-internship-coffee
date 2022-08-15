package com.coffeeshop.utilitary.converters;


import com.coffeeshop.models.coffeeRoot.Coffee;
import com.coffeeshop.models.dtos.CoffeeDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("coffeeDtoConverter")
public class CoffeeDtoConverter {

    public CoffeeDtoConverter(){};

    /**
     * @param orderedCoffees - map that contains coffees and amount from order
     * @return a list of dtos that contain certain information from the ordered coffees
     */
    public List<CoffeeDto> convertMapToListOfCoffeeDtos(Map<Coffee, Integer> orderedCoffees){
        List<CoffeeDto> convertedCoffees = new ArrayList<>();
        for(Coffee coffee: orderedCoffees.keySet()){
            String coffeeName = coffee.getCoffeeName();
            Double coffeePrice = coffee.getPrice();
            Integer quantity = orderedCoffees.get(coffee);
            CoffeeDto coffeeDto = new CoffeeDto(coffeeName, quantity, coffeePrice);
            convertedCoffees.add(coffeeDto);
        }
        return convertedCoffees;
    }
}
