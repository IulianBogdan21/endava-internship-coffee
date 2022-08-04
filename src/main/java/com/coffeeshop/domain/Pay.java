package com.coffeeshop.domain;

public class Pay {
    private final Card card;
    private final CoffeeOrder order;

    public Pay(Card card, CoffeeOrder order) {
        this.card = card;
        this.order = order;
    }

    public Card getCard() {
        return card;
    }

    public CoffeeOrder getOrder() {
        return order;
    }
}
