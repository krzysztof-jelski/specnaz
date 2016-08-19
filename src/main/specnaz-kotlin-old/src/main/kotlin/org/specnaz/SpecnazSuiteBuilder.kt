package org.specnaz

interface SpecnazSuiteBuilder {
    fun beforeAll(setup: (Nothing?) -> Unit)
    fun beforeEach(setup: (Nothing?) -> Unit)
    fun should(description: String, testBody: (Nothing?) -> Unit)
    fun afterEach(teardown: (Nothing?) -> Unit)
    fun afterAll(teardown: (Nothing?) -> Unit)

    fun spec(description: String, subSpec: (SpecnazSuiteBuilder) -> Unit)
}