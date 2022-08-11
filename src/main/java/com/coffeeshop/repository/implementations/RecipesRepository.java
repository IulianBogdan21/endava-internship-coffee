package com.coffeeshop.repository.implementations;

import com.coffeeshop.dao.implementations.RecipesDao;
import com.coffeeshop.models.coffeeRoot.Coffee;
import com.coffeeshop.repository.interfaces.CoffeeShopRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("recipesRepository")
public class RecipesRepository implements CoffeeShopRepository<Coffee> {

    private final RecipesDao recipesDao;

    public RecipesRepository(RecipesDao recipesDao){
        this.recipesDao = recipesDao;
    }

    @Override
    public List<Coffee> getAll() throws Exception {
        return recipesDao.getAll();
    }

    @Override
    public void save(Coffee entity) throws Exception {
        recipesDao.save(entity);
    }

    @Override
    public void update(Coffee entity) throws Exception {}
}
