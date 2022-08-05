package com.coffeeshop.models.customer;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Map;

@Entity
@TypeDef(name = "json", typeClass = JsonStringEncoder.class)
public class Payment {
    @Id
    private int paymentId;
    @OneToOne
    private Card customerCard;
    private String customerName;
    @Type(type = "json")
    private Map<String, Integer> orderedCoffees;

    public Payment() {}

    public Payment(int paymentId, Card customerCard, String customerName, Map<String, Integer> orderedCoffees) {
        this.paymentId = paymentId;
        this.customerCard = customerCard;
        this.customerName = customerName;
        this.orderedCoffees = orderedCoffees;
    }

    public Payment(Card customerCard, String customerName, Map<String, Integer> orderedCoffees) {
        this.customerCard = customerCard;
        this.customerName = customerName;
        this.orderedCoffees = orderedCoffees;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Card getCustomerCard() {
        return customerCard;
    }

    public void setCustomerCard(Card customerCard) {
        this.customerCard = customerCard;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Map<String, Integer> getOrderedCoffees() {
        return orderedCoffees;
    }

    public void setOrderedCoffees(Map<String, Integer> orderedCoffees) {
        this.orderedCoffees = orderedCoffees;
    }
}
