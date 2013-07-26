package com.github.groovyosgi.testing.pizzaservice.model;

public class Order {

    private final CustomerInfo customerInfo;
    private final Pizza pizza;

    public Order(Pizza pizza, CustomerInfo customerInfo) {
        this.pizza = pizza;
        this.customerInfo = customerInfo;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public Pizza getPizza() {
        return pizza;
    }

}
