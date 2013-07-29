package com.github.groovyosgi.testing.pizzaservice.impl;

import com.github.groovyosgi.testing.pizzaservice.PizzaBaker;
import com.github.groovyosgi.testing.pizzaservice.model.Pizza;

public class PizzaBakerImpl implements PizzaBaker {

    @Override
    public Pizza bake(Pizza pizza) {
        return pizza;
    }

}
