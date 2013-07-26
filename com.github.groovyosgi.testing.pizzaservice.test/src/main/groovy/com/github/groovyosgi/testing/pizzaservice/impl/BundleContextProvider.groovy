package com.github.groovyosgi.testing.pizzaservice.impl

/**
 * This class provides the {@link BundleContext} via delegating the call to the
 * {@link Activator}. This is needed in order to keep {@link Activator#getContext()}
 * package protected.
 * 
 */
class BundleContextProvider {

	static def getBundleContext(){
		return Activator.context
	}
}
