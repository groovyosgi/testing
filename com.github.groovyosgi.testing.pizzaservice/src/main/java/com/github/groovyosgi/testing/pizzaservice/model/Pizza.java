package com.github.groovyosgi.testing.pizzaservice.model;

public class Pizza {

    public enum Sauce {
        BBQ,
        HOLLANDAISE,
        TOMATO
    }

    private final boolean bacon;
    private final boolean cheese;
    private final Sauce sauce;

    public Pizza(boolean cheese, boolean bacon, Sauce sauce) {
        this.cheese = cheese;
        this.bacon = bacon;
        this.sauce = sauce;
    }

    public Sauce getSauce() {
        return sauce;
    }

    public boolean isBacon() {
        return bacon;
    }

    public boolean isCheese() {
        return cheese;
    }

}
