package com.coffeeshop.domain;

import java.util.Date;

public class Card {
    private final String cardNumber;
    private final String cardholderName;
    private final Date expiryDate;
    private final Integer civ;

    public Card(String cardNumber, String cardholderName, Date expiryDate, Integer civ) {
        this.cardNumber = cardNumber;
        this.cardholderName = cardholderName;
        this.expiryDate = expiryDate;
        this.civ = civ;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public Integer getCiv() {
        return civ;
    }
}
