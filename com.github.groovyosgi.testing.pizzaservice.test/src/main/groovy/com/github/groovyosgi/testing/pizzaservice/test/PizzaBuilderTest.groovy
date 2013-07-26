package com.github.groovyosgi.testing.pizzaservice.test;

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*
import static org.junit.matchers.JUnitMatchers.*

import org.junit.Test

import com.github.groovyosgi.testing.pizzaservice.builder.PizzaBuilder
import com.github.groovyosgi.testing.pizzaservice.model.Pizza.Sauce

class PizzaBuilderTest {

    @Test
    public void buildPizzaWithBaconCheeseAndTomateSauce() {
        def pizza = PizzaBuilder.newPizza()
                .withBacon()
                .withCheese()
                .withSauce(Sauce.TOMATO)
                .build();

        assertThat pizza.cheese, is(true)
        assertThat pizza.bacon, is(true)
        assertThat pizza.sauce, is(Sauce.TOMATO)
    }

    @Test
    public void buildPizzaWithBBQSauceOnly() {
        def pizza = PizzaBuilder.newPizza()
                .withSauce(Sauce.BBQ)
                .build();

        assertThat pizza.cheese, is(false)
        assertThat pizza.bacon, is(false)
        assertThat pizza.sauce, is(Sauce.BBQ)
    }

    @Test(expected=IllegalArgumentException)
    public void buildPizzaWithoutSauce() {
        try{
            PizzaBuilder.newPizza()
                    .withBacon()
                    .build();
        } catch(IllegalArgumentException ex) {
            assertThat ex.message, is(equalTo("Please choose a sauce."))
            throw ex
        }
    }
}
