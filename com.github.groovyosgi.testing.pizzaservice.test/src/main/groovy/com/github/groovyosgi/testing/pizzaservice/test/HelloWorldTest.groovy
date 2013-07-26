package com.github.groovyosgi.testing.pizzaservice.test

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*
import static org.junit.matchers.JUnitMatchers.*

import org.junit.Test
import org.osgi.framework.BundleContext

import com.github.groovyosgi.testing.OSGiTest
import com.github.groovyosgi.testing.paymentservice.CreditCardPaymentService
import com.github.groovyosgi.testing.pizzaservice.PizzaService
import com.github.groovyosgi.testing.pizzaservice.impl.BundleContextProvider

class HelloWorldTest extends OSGiTest{

	@Test
	public void test() {

		def transactionCalled = false

		def paymentSerive = [
			handleTransaction: {
				println "handleTransaction called"
				transactionCalled = true
			}
		] as CreditCardPaymentService


		paymentSerive.metaClass.getInterfaceName << { -> CreditCardPaymentService.class.name }

		registerMock(paymentSerive)

		PizzaService pizzaService = getService(PizzaService)
		pizzaService.placeOrder(null)

		assertThat transactionCalled, is(true)
	}

	@Override
	protected BundleContext getBundleContext() {
		return BundleContextProvider.bundleContext
	}
}
