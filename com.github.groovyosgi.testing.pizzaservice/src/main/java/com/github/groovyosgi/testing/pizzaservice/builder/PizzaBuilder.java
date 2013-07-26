package com.github.groovyosgi.testing.pizzaservice.builder;

import com.github.groovyosgi.testing.pizzaservice.model.Pizza;
import com.github.groovyosgi.testing.pizzaservice.model.Pizza.Sauce;

public class PizzaBuilder {

    public static PizzaBuilder newPizza() {
        return new PizzaBuilder();
    }

    private boolean bacon;
    private boolean cheese;

    private Sauce sauce;

    public Pizza get() {
        if (sauce == null) {
            throw new IllegalArgumentException("Please choose a sauce.");
        }

        return new Pizza(this.cheese, this.bacon, this.sauce);
    }

    public PizzaBuilder withBacon() {
        this.bacon = true;
        return this;
    }

    public PizzaBuilder withCheese() {
        this.cheese = true;
        return this;
    }

    public PizzaBuilder withSauce(Sauce sauce) {
        this.sauce = sauce;
        return this;
    }

}
