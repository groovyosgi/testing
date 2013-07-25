package com.github.groovyosgi.testing.pizzaservice.test;

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*
import static org.junit.matchers.JUnitMatchers.*

import org.junit.Test;

class HelloWorldTest {

	@Test
	public void test() {
		println "FFFFOOOOBBBAAARRR"
		assertThat true, is(true)
	}

}
