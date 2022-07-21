package utilitary;

import java.util.HashMap;
import java.util.Map;

public class PricesBuilder {
    public static Map<Ingredients, Double> buildPricesForIngredients() {
        Map<Ingredients, Double> pricesForEachIngredient = new HashMap<Ingredients, Double>();
        pricesForEachIngredient.put(Ingredients.ESPRESSO, 2.5);
        pricesForEachIngredient.put(Ingredients.BLACK_COFFEE, 1.5);
        pricesForEachIngredient.put(Ingredients.CINNAMON, 2.0);
        pricesForEachIngredient.put(Ingredients.HONEY, 3.1);
        pricesForEachIngredient.put(Ingredients.MILK_FOAM, 3.5);
        pricesForEachIngredient.put(Ingredients.STEAMED_MILK, 3.6);
        return pricesForEachIngredient;
    }
}
