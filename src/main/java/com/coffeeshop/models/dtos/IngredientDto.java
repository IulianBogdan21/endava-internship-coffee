package com.coffeeshop.models.dtos;

import com.coffeeshop.models.shop.Ingredients;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ingredients")
public class IngredientDto {
    @Id
    @Column(name = "ingredient")
    @Enumerated(EnumType.STRING)
    Ingredients ingredient;
    @Column(name = "quantity")
    Integer quantity;

    public IngredientDto() {}

    public IngredientDto(Ingredients ingredient, Integer quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public Ingredients getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredients ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientDto that = (IngredientDto) o;
        return ingredient == that.ingredient;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredient);
    }

    @Override
    public String toString() {
        return "IngredientDto{" +
                "ingredient=" + ingredient +
                ", quantity=" + quantity +
                '}';
    }
}
