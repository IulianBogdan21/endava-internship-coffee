package com.coffeeshop.dao.implementations;

import com.coffeeshop.dao.interfaces.CoffeeShopDao;
import com.coffeeshop.models.coffeeRoot.Coffee;
import com.coffeeshop.models.dtos.IngredientDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("recipesDAO")
@DependsOn(value = "sessionFactory")
public class RecipesDao implements CoffeeShopDao<Coffee>{

    private final SessionFactory sessionFactory;

    @Autowired
    public RecipesDao(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Coffee> getAll() throws Exception {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Coffee> coffees = session.createQuery("from Coffee", Coffee.class).list();
            session.getTransaction().commit();
            return coffees;
        }
        catch (Exception exception){
            throw new Exception(exception.getMessage());
        }
    }

    @Override
    public void update(Coffee entity) throws Exception {

    }

    @Override
    public void save(Coffee entity) throws Exception {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        }
        catch (Exception exception){
            throw new Exception(exception.getMessage());
        }
    }
}
