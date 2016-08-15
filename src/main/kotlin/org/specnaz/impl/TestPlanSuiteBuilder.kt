package org.specnaz.impl

import org.specnaz.SpecnazSuiteBuilder

class TestPlanSuiteBuilder() : SpecnazSuiteBuilder {
    var plannedTests: List<PlannedTest> = emptyList()

    override fun should(description: String, testBody: (Nothing?) -> Unit) {
        plannedTests += PlannedTest(description)
    }

    override fun beforeAll(setup: (Nothing?) -> Unit) {
    }

    override fun beforeEach(setup: (Nothing?) -> Unit) {
    }

    override fun afterEach(teardown: (Nothing?) -> Unit) {
    }

    override fun afterAll(teardown: (Nothing?) -> Unit) {
    }
}