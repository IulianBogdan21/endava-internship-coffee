package com.coffeeshop.repository.implementations;

import com.coffeeshop.models.dtos.IngredientDto;
import com.coffeeshop.repository.interfaces.IIngredientsRepository;
import com.coffeeshop.models.shop.Ingredients;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("ingredientsRepository")
@DependsOn(value = "sessionFactory")
public class IngredientsRepository implements IIngredientsRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public IngredientsRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Map<Ingredients, Integer> getAll() throws Exception {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<IngredientDto> ingredientsDtoToMap = session.createQuery("from IngredientDto", IngredientDto.class).list();
            Map<Ingredients, Integer> createIngredientsMap = new HashMap<>();
            for(IngredientDto ingredientDto: ingredientsDtoToMap){
                createIngredientsMap.put(ingredientDto.getIngredient(), ingredientDto.getQuantity());
            }
            session.getTransaction().commit();
            return createIngredientsMap;
        }
        catch (Exception exception){
            throw new Exception(exception.getMessage());
        }
    }

    /**
     * method updates the current stock of ingredients after preparing a coffee
     * @param consumedIngredients - ingredients consumed after making a certain coffee
     */
    @Override
    public void updateIngredients(Map<Ingredients, Integer> consumedIngredients){
        /*for(Ingredients iteratedConsumedIngredient: consumedIngredients.keySet()){
            int updatedQuantityForIngredient = stockOfIngredients.get(iteratedConsumedIngredient) -
                    consumedIngredients.get(iteratedConsumedIngredient);
            stockOfIngredients.put(iteratedConsumedIngredient, updatedQuantityForIngredient);
        }*/
    }
}
