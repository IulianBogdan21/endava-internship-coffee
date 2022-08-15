package com.coffeeshop.models.dtos;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class CoffeeDto {
    private String coffeeName;
    private Integer amount;
    private Double price;

    public CoffeeDto() {}

    public CoffeeDto(String coffeeName, Integer amount, Double price) {
        this.coffeeName = coffeeName;
        this.amount = amount;
        this.price = price;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoffeeDto coffeeDto = (CoffeeDto) o;
        return Objects.equals(coffeeName, coffeeDto.coffeeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coffeeName);
    }

    @Override
    public String toString() {
        return "CoffeeDto{" +
                "coffeeName='" + coffeeName + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
