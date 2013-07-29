package com.github.groovyosgi.testing.pizzaservice;

import com.github.groovyosgi.testing.pizzaservice.model.Order;

public interface PizzaService {

    static final String EVENT_PROPERTY_CUSTOMER_INFO = "CUSTOMER_INFO";
    static final String EVENT_PROPERTY_ID = "ID";
    static final String EVENT_PROPERTY_PIZZA = "PIZZA";
    static final String EVENT_TOPIC_PIZZA_DELIVERY = "PIZZA_DELIVERY";

    void placeOrder(Order order);

}
