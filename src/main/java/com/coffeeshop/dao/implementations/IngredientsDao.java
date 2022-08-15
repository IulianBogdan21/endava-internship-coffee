package com.coffeeshop.dao.implementations;

import com.coffeeshop.models.dtos.IngredientDto;
import com.coffeeshop.dao.interfaces.CoffeeShopDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ingredientsDAO")
@DependsOn(value = "sessionFactory")
public class IngredientsDao implements CoffeeShopDao<IngredientDto> {

    private final SessionFactory sessionFactory;

    @Autowired
    public IngredientsDao(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    /**
     * @return all ingredients and their amount from the database
     * @throws Exception - exceptions if any issues with the database connection
     */
    @Override
    public List<IngredientDto> getAll() throws Exception {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<IngredientDto> ingredientsFromDatabase = session.createQuery("from IngredientDto", IngredientDto.class).list();
            session.getTransaction().commit();
            return ingredientsFromDatabase;
        }
        catch (Exception exception){
            throw new Exception(exception.getMessage());
        }
    }

    /**
     * method updates the current stock of specified ingredient after preparing a coffee
     * @param ingredientAndConsumedQuantity - ingredient consumed after making a certain coffee and amount of it
     * the database with ingredients will be updated accordingly
     */
    @Override
    public void update(IngredientDto ingredientAndConsumedQuantity) throws Exception{
        try(Session session = sessionFactory.openSession()){
                session.beginTransaction();
                Query updateQuery = session.createQuery("update IngredientDto set quantity=(quantity - :quantityParam) where ingredient=:ingredientParam");
                updateQuery.setParameter("quantityParam", ingredientAndConsumedQuantity.getQuantity());
                updateQuery.setParameter("ingredientParam", ingredientAndConsumedQuantity.getIngredient());
                updateQuery.executeUpdate();
                session.getTransaction().commit();
            }
        catch (Exception exception){
            throw new Exception(exception.getMessage());
        }
    }
}
