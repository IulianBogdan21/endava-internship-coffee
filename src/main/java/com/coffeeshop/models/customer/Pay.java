package com.coffeeshop.models.customer;

import java.util.Map;

public class Pay {
    private String cardNumber;
    private String cardholderName;
    private String expiryDate;
    private Integer civ;
    private String customerName;
    private Map<String, Integer> orderedCoffees;

    public Pay(String cardNumber, String cardholderName, String expiryDate, Integer civ, String customerName, Map<String, Integer> orderedCoffees) {
        this.cardNumber = cardNumber;
        this.cardholderName = cardholderName;
        this.expiryDate = expiryDate;
        this.civ = civ;
        this.customerName = customerName;
        this.orderedCoffees = orderedCoffees;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getCiv() {
        return civ;
    }

    public void setCiv(Integer civ) {
        this.civ = civ;
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
