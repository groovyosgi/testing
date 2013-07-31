package com.github.groovyosgi.testing.pizzaservice.test

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*
import static org.junit.matchers.JUnitMatchers.*

import org.junit.Before
import org.junit.Test
import org.osgi.framework.BundleContext
import org.osgi.service.event.Event
import org.osgi.service.event.EventAdmin
import org.osgi.service.event.EventHandler

import com.github.groovyosgi.testing.OSGiTest
import com.github.groovyosgi.testing.pizzaservice.PizzaDeliveryMan
import com.github.groovyosgi.testing.pizzaservice.PizzaService
import com.github.groovyosgi.testing.pizzaservice.builder.PizzaBuilder
import com.github.groovyosgi.testing.pizzaservice.impl.Activator
import com.github.groovyosgi.testing.pizzaservice.model.Address
import com.github.groovyosgi.testing.pizzaservice.model.CustomerInfo
import com.github.groovyosgi.testing.pizzaservice.model.Pizza.Sauce

class PizzaDeliveryManTest extends OSGiTest{

    EventAdmin eventAdmin

    @Before
    void setUp() {
        eventAdmin = getService(EventAdmin)
    }

    @Test
    public void deliverPizzaOnEvent() {

        def eventHandlerProperties = ['event.topics': PizzaDeliveryMan.EVENT_TOPIC_PIZZA_DELIVERED] as Hashtable

        def pizzaHasBeenDelivered = false

        registerMock([
            handleEvent: { Event event ->
                pizzaHasBeenDelivered = true
                assertThat event.topic, is(equalTo(PizzaDeliveryMan.EVENT_TOPIC_PIZZA_DELIVERED))
                assertThat event.getProperty(PizzaDeliveryMan.EVENT_PROPERTY_ID), is(1)
            }
        ] as EventHandler, EventHandler, eventHandlerProperties)

        def customer = new CustomerInfo("Max Mustermann", new Address(), 54325548936 as short)
        def pizza = PizzaBuilder.newPizza().withSauce(Sauce.BBQ).build()

        def properties = [
            (PizzaService.EVENT_PROPERTY_ID): 1,
            (PizzaService.EVENT_PROPERTY_CUSTOMER_INFO): customer,
            (PizzaService.EVENT_PROPERTY_PIZZA): pizza
        ] as Hashtable

        eventAdmin.postEvent(new Event(PizzaService.EVENT_TOPIC_PIZZA_DELIVERY, properties))

        waitFor({pizzaHasBeenDelivered})

        assertThat pizzaHasBeenDelivered, is(true)
    }

    private waitFor(Closure<?> condition, int timeout = 1000, int sleepTime = 50) {
        def waitingTime = 0;
        while(!condition() && waitingTime < timeout) {
            waitingTime += sleepTime
            sleep sleepTime
        }
    }

    @Override
    protected BundleContext getBundleContext() {
        return Activator.context
    }
}
