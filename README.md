# OSGi-Tests the Groovy way
[![Build Status](https://travis-ci.org/groovyosgi/testing.png?branch=master)](https://travis-ci.org/groovyosgi/testing)

This sample project illustrates how you could write OSGi tests in Groovy. It also provides a Maven Tycho configuration which builds the bundles and executes the tests.

## Build & Test via Maven Tycho

```shell
$ git clone https://github.com/groovyosgi/testing.git   
$ cd testing   
$ mvn clean install   
```

## Eclipse
* Import the bundles and the test fragment via "File->Import->General->Existing Projects into Workspace".
* In order to execute the tests from Eclipse, execute `PizzaDeliveryManTest.launch` and `PizzaServiceTest.launch` via "Run As".

## EclipseCon Europe / OSGi Community Event 2013 Slides:
[http://groovyosgi.github.io/testing/](http://groovyosgi.github.io/testing/)