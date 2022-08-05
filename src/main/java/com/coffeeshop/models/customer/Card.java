package com.coffeeshop.models.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Card {
    @Id
    private String cardNumber;
    private String cardholderName;
    private String expiryDate;
    private Integer civ;

    public Card() {}

    public Card(String cardNumber, String cardholderName, String expiryDate, Integer civ) {
        this.cardNumber = cardNumber;
        this.cardholderName = cardholderName;
        this.expiryDate = expiryDate;
        this.civ = civ;
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
}
