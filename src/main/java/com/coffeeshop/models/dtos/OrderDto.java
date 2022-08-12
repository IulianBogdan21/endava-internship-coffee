package com.coffeeshop.models.dtos;

import com.coffeeshop.models.customer.OrderStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class OrderDto {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "order_id", unique = true)
    private Integer orderId;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "order_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime timeOfOrdering;
    @Column(name = "order_type")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<CoffeeDto> coffeesFromOrder;
    @Column(name = "total_cost")
    private Double costOfOrder;

    public OrderDto() {}

    public OrderDto(Integer orderId, String customerName, LocalDateTime timeOfOrdering,
                    OrderStatus orderStatus, List<CoffeeDto> coffeesFromOrder, Double costOfOrder) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.timeOfOrdering = timeOfOrdering;
        this.orderStatus = orderStatus;
        this.coffeesFromOrder = coffeesFromOrder;
        this.costOfOrder = costOfOrder;
    }

    public OrderDto(String customerName, LocalDateTime timeOfOrdering,
                    OrderStatus orderStatus, List<CoffeeDto> coffeesFromOrder, Double costOfOrder) {
        this.customerName = customerName;
        this.timeOfOrdering = timeOfOrdering;
        this.orderStatus = orderStatus;
        this.coffeesFromOrder = coffeesFromOrder;
        this.costOfOrder = costOfOrder;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getTimeOfOrdering() {
        return timeOfOrdering;
    }

    public void setTimeOfOrdering(LocalDateTime timeOfOrdering) {
        this.timeOfOrdering = timeOfOrdering;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<CoffeeDto> getCoffeesFromOrder() {
        return coffeesFromOrder;
    }

    public void setCoffeesFromOrder(List<CoffeeDto> coffeesFromOrder) {
        this.coffeesFromOrder = coffeesFromOrder;
    }

    public Double getCostOfOrder() {
        return costOfOrder;
    }

    public void setCostOfOrder(Double costOfOrder) {
        this.costOfOrder = costOfOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(orderId, orderDto.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "customerName='" + customerName + '\'' +
                ", timeOfOrdering=" + timeOfOrdering +
                ", orderStatus=" + orderStatus +
                ", coffeesFromOrder=" + coffeesFromOrder +
                ", costOfOrder=" + costOfOrder +
                '}';
    }
}
