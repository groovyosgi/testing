package com.github.groovyosgi.testing

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*
import static org.junit.matchers.JUnitMatchers.*

import org.junit.After
import org.junit.Before
import org.osgi.framework.BundleContext
import org.osgi.framework.ServiceReference

abstract class OSGiTest {

	private static BundleContext bundleContext
	private def registeredServices = [:]

	protected abstract BundleContext getBundleContext()

	@Before
	void bindBundleContext() {
		bundleContext = getBundleContext()
		assertNotNull("Bundle context must not be null for OSGi tests.", bundleContext)
	}

	def getService(Class clazz){
		ServiceReference<?> serviceReference = bundleContext.getServiceReference(clazz.name)

		assertThat serviceReference, is(notNullValue())

		return bundleContext.getService(serviceReference)
	}

	def registerMock(def mock) {
		registeredServices.put(mock.interfaceName, bundleContext.registerService(mock.interfaceName, mock, null))
	}

	def unregisterMock(def mock) {
		registeredServices.get(mock.interfaceName).unregister()
		registeredServices.remove(mock.interfaceName)
	}

	@After
	void unregisterMocks(){
		registeredServices.each() { interfaceName, service ->
			service.unregister()
		}
		registeredServices.clear()
	}
}
