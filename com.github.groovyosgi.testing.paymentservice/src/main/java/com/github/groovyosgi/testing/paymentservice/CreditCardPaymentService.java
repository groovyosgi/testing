package com.github.groovyosgi.testing.paymentservice;

public interface CreditCardPaymentService {

    void handleTransaction(String companyId, long creditCardNumber, String cardHolderName, float price);

}
