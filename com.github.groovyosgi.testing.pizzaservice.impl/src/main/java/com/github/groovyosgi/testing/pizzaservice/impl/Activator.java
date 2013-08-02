package com.github.groovyosgi.testing.pizzaservice.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public final class Activator implements BundleActivator {

    private static BundleContext context;

    static BundleContext getContext() {
        return context;
    }

    @Override
    public void start(final BundleContext bundleContext) {
        Activator.context = bundleContext;
    }

    @Override
    public void stop(final BundleContext bundleContext) {
        Activator.context = null;
    }

}
