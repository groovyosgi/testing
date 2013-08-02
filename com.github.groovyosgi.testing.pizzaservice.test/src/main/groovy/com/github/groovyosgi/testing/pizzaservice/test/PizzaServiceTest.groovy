package com.github.groovyosgi.testing.pizzaservice.test

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*
import static org.junit.matchers.JUnitMatchers.*

import org.junit.Test
import org.osgi.framework.BundleContext

import com.github.groovyosgi.testing.OSGiTest
import com.github.groovyosgi.testing.paymentservice.CreditCardPaymentService
import com.github.groovyosgi.testing.pizzaservice.PizzaService
import com.github.groovyosgi.testing.pizzaservice.builder.PizzaBuilder
import com.github.groovyosgi.testing.pizzaservice.impl.Activator
import com.github.groovyosgi.testing.pizzaservice.model.Address
import com.github.groovyosgi.testing.pizzaservice.model.CustomerInfo
import com.github.groovyosgi.testing.pizzaservice.model.Order
import com.github.groovyosgi.testing.pizzaservice.model.Pizza.Sauce

class PizzaServiceTest extends OSGiTest{

    @Test
    public void test() {

        def transactionCalled = false

        def paymentService = [
            handleTransaction: {
                println "handleTransaction called"
                transactionCalled = true
            }
        ] as CreditCardPaymentService

        registerMock(paymentService)

        PizzaService pizzaService = getService(PizzaService)
        def pizza = PizzaBuilder.newPizza().withSauce(Sauce.BBQ).build()
        def customerInfo = new CustomerInfo("Max Mustermann", new Address(), 524030324560 as short)
        pizzaService.placeOrder(new Order(pizza, customerInfo))

        assertThat transactionCalled, is(true)
    }

    @Override
    protected BundleContext getBundleContext() {
        return Activator.context
    }
}
