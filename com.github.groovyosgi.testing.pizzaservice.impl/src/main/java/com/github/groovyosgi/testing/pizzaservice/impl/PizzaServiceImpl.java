package com.github.groovyosgi.testing.pizzaservice.impl;

import java.util.HashMap;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

import com.github.groovyosgi.testing.paymentservice.CreditCardPaymentService;
import com.github.groovyosgi.testing.pizzaservice.PizzaBaker;
import com.github.groovyosgi.testing.pizzaservice.PizzaService;
import com.github.groovyosgi.testing.pizzaservice.model.CustomerInfo;
import com.github.groovyosgi.testing.pizzaservice.model.Order;
import com.github.groovyosgi.testing.pizzaservice.model.Pizza;

public class PizzaServiceImpl implements PizzaService {

    private static final String PAYMENT_SERVICE_COMPANY_ID = "LUIGIS_PIZZA_SERVICE";
    private static final String TAG = PizzaServiceImpl.class.getName();
    private CreditCardPaymentService creditCardPaymentService;
    private EventAdmin eventAdmin;
    private int orderNumber = 0;
    private PizzaBaker pizzaBaker;

    @Override
    public void placeOrder(final Order order) {

        CustomerInfo customerInfo = order.getCustomerInfo();
        Pizza orderedPizza = order.getPizza();

        float price = calculatePrice(orderedPizza);

        creditCardPaymentService.handleTransaction(PAYMENT_SERVICE_COMPANY_ID, customerInfo.getCreditCardNumber(),
                customerInfo.getName(), price);

        Pizza pizza = pizzaBaker.bake(orderedPizza);

        sendDeliveryEvent(order, pizza);
    }

    private float calculatePrice(Pizza orderedPizza) {

        float price = 3;

        if (orderedPizza.isBacon()) {
            price += 1;
        }
        if (orderedPizza.isCheese()) {
            price += 0.5;
        }

        switch (orderedPizza.getSauce()) {
            case BBQ:
                price += 2;
                break;
            case HOLLANDAISE:
                price += 1.5;
                break;
            case TOMATO:
                price += 1;
                break;
            default:
                break;
        }

        return price;
    }

    private void sendDeliveryEvent(final Order order, Pizza pizza) {

        HashMap<String, Object> properties = new HashMap<>();
        properties.put(EVENT_PROPERTY_ID, orderNumber++);
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

    void setCreditCardPaymentService(final CreditCardPaymentService creditCardPaymentService) {
        this.creditCardPaymentService = creditCardPaymentService;
    }

    void setEventAdmin(EventAdmin eventAdmin) {
        this.eventAdmin = eventAdmin;
    }

    void setPizzaBaker(PizzaBaker pizzaBaker) {
        this.pizzaBaker = pizzaBaker;
    }

    void unsetCreditCardPaymentService(final CreditCardPaymentService creditCardPaymentService) {
        this.creditCardPaymentService = null;
    }

    void unsetEventAdmin(EventAdmin eventAdmin) {
        this.eventAdmin = null;
    }

    void unsetPizzaBaker(PizzaBaker pizzaBaker) {
        this.pizzaBaker = null;
    }
}
