package com.coffeeshop.service.implementations;

import com.coffeeshop.models.customer.Card;
import org.springframework.stereotype.Service;

@Service("cardValidationService")
public class CardValidationService {

    public CardValidationService(){}

    public boolean isCardValid(Card cardToValidate){
        return isNameValid(cardToValidate.getCardholderName())
                && isCivValid(cardToValidate.getCiv())
                && isDateValid(cardToValidate.getExpiryDate())
                && isCardNumberValid(cardToValidate.getCardNumber());
    }

    /**
     * @param cardNumber - String - number of the card 16 digits
     * @return true if card is valid false otherwise
     * using Luhn algorithm to validate number
     */
    private boolean isCardNumberValid(String cardNumber) {
        String preconditionRegex = "[0-9]{16}";
        if(cardNumber.matches(preconditionRegex)){
            int LENGTH_OF_CARD_NUMBER = 16;
            int evaluatedSumOfDigits = 0;
            for(int rank = LENGTH_OF_CARD_NUMBER - 1; rank >= 0; rank--){
                int digitOnCurrentRank = Character.getNumericValue(cardNumber.charAt(rank));
                if((LENGTH_OF_CARD_NUMBER - rank) % 2 == 0){
                    int doubleTheCurrentDigit = digitOnCurrentRank * 2;
                    if(doubleTheCurrentDigit > 9)
                        doubleTheCurrentDigit -= 9;
                    evaluatedSumOfDigits += doubleTheCurrentDigit;
                }
                else{
                    evaluatedSumOfDigits += digitOnCurrentRank;
                }
            }
            return evaluatedSumOfDigits % 10 == 0;
        }
        return false;
    }

    /**
     * @param expiryDate - String - the expiry date of the card
     * @return true if date is valid, false otherwise
     * accepted format: mm\yy
     * assume expiration years are allowed until 2039
     */
    private boolean isDateValid(String expiryDate) {
        String regularDateRegex = "[12][0-9]/[23][0-9]";
        String zeroFirstRegex = "[0][1-9]/[23][0-9]";
        String threeFirstRegex = "[3][01]/[23][0-9]";
        return expiryDate.matches(regularDateRegex) || expiryDate.matches(zeroFirstRegex) || expiryDate.matches(threeFirstRegex);
    }

    /**
     * @param civ - String - the civ of the card
     * @return true if the civ is valid, false otherwise
     * the civ must be a 3 digits number to be valid
     */
    private boolean isCivValid(String civ) {
        String civRegex = "[1-9][0-9]{2}";
        return civ.matches(civRegex);
    }

    /**
     * @param cardholderName String - card's owner's name
     * @return true if the name is valid, false otherwise
     * formats for first name and last name: alphabetic or: '`~.- maximum 24
     */
    private boolean isNameValid(String cardholderName) {
        String cardholderNameRegex = "[A-Z '`~[.]-]{1,24} [A-Z '`~[.]-]{1,24}";
        return cardholderName.matches(cardholderNameRegex);
    }
}
