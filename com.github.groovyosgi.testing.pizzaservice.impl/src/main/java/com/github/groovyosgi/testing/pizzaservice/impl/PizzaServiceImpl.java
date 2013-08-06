package com.github.groovyosgi.testing.pizzaservice.impl;

import java.util.HashMap;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

import com.github.groovyosgi.testing.paymentservice.CreditCardPaymentService;
import com.github.groovyosgi.testing.pizzaservice.PizzaBaker;
import com.github.groovyosgi.testing.pizzaservice.PizzaService;
import com.github.groovyosgi.testing.pizzaservice.model.Order;
import com.github.groovyosgi.testing.pizzaservice.model.Pizza;

public class PizzaServiceImpl implements PizzaService {

    private static final String TAG = PizzaServiceImpl.class.getName();
    private CreditCardPaymentService creditCardPaymentService;
    private EventAdmin eventAdmin;
    private PizzaBaker pizzaBaker;

    @Override
    public void placeOrder(final Order order) {
        creditCardPaymentService.handleTransaction();
        Pizza pizza = pizzaBaker.bake(order.getPizza());
        sendDeliveryEvent(order, pizza);
    }

    private void sendDeliveryEvent(final Order order, Pizza pizza) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put(EVENT_PROPERTY_PIZZA, pizza);
        properties.put(EVENT_PROPERTY_CUSTOMER_INFO, order.getCustomerInfo());
        eventAdmin.postEvent(new Event(EVENT_TOPIC_PIZZA_DELIVERY, properties));
    }

    void activate(final ComponentContext componentContext) {
        System.out.println(TAG + " started!");
    }

    void deactivate(final ComponentContext componentContext) {
        System.out.println(TAG + " stoped!");
    }

    void setCreditCardPaymentService(
            final CreditCardPaymentService creditCardPaymentService) {
        this.creditCardPaymentService = creditCardPaymentService;
    }
    void setEventAdmin(EventAdmin eventAdmin) {
        this.eventAdmin = eventAdmin;
    }

    void setPizzaBaker(PizzaBaker pizzaBaker) {
        this.pizzaBaker = pizzaBaker;
    }

    void unsetCreditCardPaymentService(
            final CreditCardPaymentService creditCardPaymentService) {
        this.creditCardPaymentService = null;
    }

    void unsetEventAdmin(EventAdmin eventAdmin) {
        this.eventAdmin = null;
    }

    void unsetPizzaBaker(PizzaBaker pizzaBaker) {
        this.pizzaBaker = null;
    }
}
