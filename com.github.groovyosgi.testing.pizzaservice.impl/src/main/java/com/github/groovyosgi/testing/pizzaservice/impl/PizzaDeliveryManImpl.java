package com.github.groovyosgi.testing.pizzaservice.impl;

import java.util.HashMap;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventHandler;

import com.github.groovyosgi.testing.pizzaservice.PizzaDeliveryMan;
import com.github.groovyosgi.testing.pizzaservice.PizzaService;
import com.github.groovyosgi.testing.pizzaservice.model.CustomerInfo;
import com.github.groovyosgi.testing.pizzaservice.model.Pizza;

public class PizzaDeliveryManImpl implements PizzaDeliveryMan, EventHandler {

    private EventAdmin eventAdmin;

    @Override
    public void handleEvent(Event event) {

        Pizza pizza = (Pizza) event.getProperty(PizzaService.EVENT_PROPERTY_PIZZA);
        CustomerInfo customerInfo = (CustomerInfo) event.getProperty(PizzaService.EVENT_PROPERTY_CUSTOMER_INFO);
        int id = (int) event.getProperty(PizzaService.EVENT_PROPERTY_ID);

        deliver(pizza, customerInfo);

        sendPizzaDeliveredEvent(id);
    }

    private void deliver(Pizza pizza, CustomerInfo customerInfo) {

    }

    private void sendPizzaDeliveredEvent(int id) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put(EVENT_PROPERTY_ID, id);
        eventAdmin.postEvent(new Event(EVENT_TOPIC_PIZZA_DELIVERED, properties));
    }

    void activate(ComponentContext componentContext) {

    }

    void setEventAdmin(EventAdmin eventAdmin) {
        this.eventAdmin = eventAdmin;
    }

    void unsetEventAdmin(EventAdmin eventAdmin) {
        this.eventAdmin = null;
    }
}
