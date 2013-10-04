package com.github.groovyosgi.testing.pizzaservice.test.builder

import static com.github.groovyosgi.testing.pizzaservice.model.Pizza.Sauce.*
import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*
import static org.junit.matchers.JUnitMatchers.*

import org.junit.Test

import com.github.groovyosgi.testing.pizzaservice.builder.PizzaBuilder

class PizzaBuilderTest {

    @Test
    void buildPizzaWithBaconCheeseAndTomateSauce() {
        def pizza = PizzaBuilder.newPizza()
                .withBacon()
                .withCheese()
                .withSauce(TOMATO)
                .build()

        assertThat pizza.cheese, is(true)
        assertThat pizza.bacon, is(true)
        assertThat pizza.sauce, is(TOMATO)
    }

    @Test
    void buildPizzaWithBBQSauceOnly() {
        def pizza = PizzaBuilder.newPizza()
                .withSauce(BBQ)
                .build()

        assertThat pizza.cheese, is(false)
        assertThat pizza.bacon, is(false)
        assertThat pizza.sauce, is(BBQ)
    }

    @Test(expected=IllegalArgumentException)
    void buildPizzaWithoutSauce() {
        try{
            PizzaBuilder.newPizza()
                    .withBacon()
                    .build()
        } catch(IllegalArgumentException ex) {
            assertThat ex.message, is(equalTo("Please choose a sauce."))
            throw ex
        }
    }
}
