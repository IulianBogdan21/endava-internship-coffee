package com.coffeeshop.dao.implementations;

import com.coffeeshop.dao.interfaces.CoffeeShopDao;
import com.coffeeshop.models.dtos.OrderDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ordersDAO")
@DependsOn(value = "sessionFactory")
public class OrdersDao implements CoffeeShopDao<OrderDto>{

    private final SessionFactory sessionFactory;

    @Autowired
    public OrdersDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<OrderDto> getAll() throws Exception {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<OrderDto> ordersFromDatabase = session.createQuery("from OrderDto", OrderDto.class).list();
            session.getTransaction().commit();
            return ordersFromDatabase;
        }
        catch (Exception exception){
            throw new Exception(exception.getMessage());
        }
    }

    @Override
    public void update(OrderDto entity) throws Exception {

    }

    @Override
    public void save(OrderDto entity) throws Exception {
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
