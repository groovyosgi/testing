package com.github.groovyosgi.testing.pizzaservice.impl;

import org.osgi.service.component.ComponentContext;

import com.github.groovyosgi.testing.paymentservice.CreditCardPaymentService;
import com.github.groovyosgi.testing.pizzaservice.PizzaService;
import com.github.groovyosgi.testing.pizzaservice.model.Order;

public class PizzaSeriveImpl implements PizzaService {

	private static final String TAG = PizzaSeriveImpl.class.getName();
	private CreditCardPaymentService creditCardPaymentService;

	public void activate(final ComponentContext componentContext) {
		System.out.println(TAG + " started!");
	}

	public void deactivate(final ComponentContext componentContext) {
		System.out.println(TAG + " stoped!");
	}

	@Override
	public void placeOrder(final Order order) {
		creditCardPaymentService.handleTransaction();
	}

	public void setCreditCardPaymentService(
			final CreditCardPaymentService creditCardPaymentService) {
		this.creditCardPaymentService = creditCardPaymentService;
	}

	public void unsetCreditCardPaymentService(
			final CreditCardPaymentService creditCardPaymentService) {
		this.creditCardPaymentService = null;
	}
}
