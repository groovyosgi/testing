package com.github.groovyosgi.testing.pizzaservice.model;

public class CustomerInfo {

    private final Address address;
    private final long creditCardNumber;
    private final String name;

    public CustomerInfo(String name, Address address, long creditCardNumber) {
        this.name = name;
        this.address = address;
        this.creditCardNumber = creditCardNumber;
    }

    public Address getAddress() {
        return address;
    }

    public long getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getName() {
        return name;
    }

}
