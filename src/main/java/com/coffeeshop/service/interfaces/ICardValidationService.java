package com.coffeeshop.service.interfaces;

import com.coffeeshop.models.customer.Card;

public interface ICardValidationService {
    void validateCard(Card cardToValidate) throws Exception;
}
