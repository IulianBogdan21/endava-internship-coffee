package com.coffeeshop.dao.implementations;

import com.coffeeshop.dao.interfaces.CoffeeShopDao;
import com.coffeeshop.models.coffeeRoot.Coffee;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("recipesDAO")
@DependsOn(value = "sessionFactory")
public class RecipesDao implements CoffeeShopDao<Coffee>{

    @Override
    public List<Coffee> getAll() throws Exception {
        return null;
    }

    @Override
    public void update(Coffee entity) throws Exception {

    }

    @Override
    public void save(Coffee entity) {

    }
}
