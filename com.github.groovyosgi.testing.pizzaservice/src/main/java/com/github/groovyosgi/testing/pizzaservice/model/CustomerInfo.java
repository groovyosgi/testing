package com.github.groovyosgi.testing.pizzaservice.model;

public class CustomerInfo {

    private final Address address;
    private final short creditCardNumber;
    private final String name;

    public CustomerInfo(String name, Address address, short creditCardNumber) {
        this.name = name;
        this.address = address;
        this.creditCardNumber = creditCardNumber;
    }

    public Address getAddress() {
        return address;
    }

    public short getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getName() {
        return name;
    }

}
