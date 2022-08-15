package com.coffeeshop.repository.implementations;

import com.coffeeshop.dao.implementations.IngredientsDao;
import com.coffeeshop.models.dtos.IngredientDto;
import com.coffeeshop.repository.interfaces.CoffeeShopRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ingredientsRepository")
public class IngredientsRepository implements CoffeeShopRepository<IngredientDto> {

    private final IngredientsDao ingredientsDAO;

    public IngredientsRepository(IngredientsDao ingredientsDAO){
        this.ingredientsDAO = ingredientsDAO;
    }

    @Override
    public List<IngredientDto> getAll() throws Exception {
        return ingredientsDAO.getAll();
    }

    @Override
    public void save(IngredientDto entity) {}

    @Override
    public void update(IngredientDto entity) throws Exception {
        ingredientsDAO.update(entity);
    }
}
