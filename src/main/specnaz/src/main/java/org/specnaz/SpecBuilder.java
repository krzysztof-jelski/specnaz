package org.specnaz;

import org.specnaz.utils.TestClosure;
import org.specnaz.utils.ThrowableExpectations;

/**
 * The class used to create the specification.
 * An instance of this interface is passed to the
 * {@link Specnaz#describes} method to build the
 * test plan, and then later execute it.
 *
 * @see #should
 * @see #shouldThrow
 * @see #beginsEach
 * @see #beginsAll
 * @see #endsEach
 * @see #endsAll
 * @see #describes
 * @see #fshould
 * @see #fshouldThrow
 * @see #fdescribes
 * @see #xshould
 * @see #xshouldThrow
 * @see #xdescribes
 */
public interface SpecBuilder {
    /**
     * A lifecycle callback executed only once for each test group.
     * The equivalent of RSpec's or Jasmine's {@code beforeAll}.
     * <p>
     * You can have any number of them in each group,
     * and they are guaranteed to run in the order they were declared.
     * <p>
     * Nested groups execute all of their ancestor's {@code beginsAll} callbacks.
     * They are executed in "outside-in" order -
     * meaning, parent ones run before the child's.
     *
     * @param closure
     *     the body of the callback
     *
     * @see #should
     * @see #shouldThrow
     * @see #beginsEach
     * @see #endsEach
     * @see #endsAll
     * @see #describes
     * @see #fshould
     * @see #fshouldThrow
     * @see #fdescribes
     * @see #xshould
     * @see #xshouldThrow
     * @see #xdescribes
     */
    void beginsAll(TestClosure closure);

    /**
     * A lifecycle callback executed before each test case.
     * The equivalent of RSpec's or Jasmine's {@code beforeEach}.
     * <p>
     * You can have any number of them in each group,
     * and they are guaranteed to run in the order they were declared.
     * <p>
     * Nested groups execute all of their ancestor's {@code beginsEach} callbacks.
     * They are executed in "outside-in" order -
     * meaning, parent ones run before the child's.
     *
     * @param closure
     *     the body of the callback
     *
     * @see #should
     * @see #shouldThrow
     * @see #beginsAll
     * @see #endsEach
     * @see #endsAll
     * @see #describes
     * @see #fshould
     * @see #fshouldThrow
     * @see #fdescribes
     * @see #xshould
     * @see #xshouldThrow
     * @see #xdescribes
     */
    void beginsEach(TestClosure closure);

    /**
     * Defines a test.
     * <p>
     * You can declare any number of them in each group.
     *
     * @param description
     *     the description of this test.
     *     It will serve as the name for this test in the reports.
     *     Note that the word 'should' will be prepended to this description
     *     by the library
     * @param testBody
     *     the body of the test case
     * @return
     *     an instance of the {@link TestSettings} class
     *
     * @see #shouldThrow
     * @see #beginsEach
     * @see #beginsAll
     * @see #endsEach
     * @see #endsAll
     * @see #describes
     * @see #fshould
     * @see #fshouldThrow
     * @see #fdescribes
     * @see #xshould
     * @see #xshouldThrow
     * @see #xdescribes
     */
    TestSettings should(String description, TestClosure testBody);

    /**
     * Define a test expecting an Exception to be thrown.
     * <p>
     * This is very similar to the {@link #should} method, except the test passes
     * only if it results in an Exception (of type {@code expectedException})
     * being thrown, similarly to JUnit's {@code @Test.expected}.
     * It returns an instance of the {@link ThrowableExpectations} class,
     * which allows you to refine the conditions that the thrown exception
     * has to satisfy in order for the test to pass even further.
     *
     * @param expectedException
     *     the type of Exception expected from the test.
     *     The Exception resulting from executing the test must be of this type
     *     (so, be an instance of either the {@code expectedException} class,
     *     or a subclass of {@code expectedException})
     *     for the test to have been deemed passing
     * @param description
     *     the description of this test.
     *     It will serve as the name for this test in the reports.
     *     Note that the words 'should throw &lt;ExpectedExceptionClass&gt;'
     *     will be prepended to it by the library
     * @param testBody
     *     the body of the test case
     * @return
     *     a new instance of the {@link ThrowableExpectations} class
     *
     * @see #should
     * @see #beginsEach
     * @see #beginsAll
     * @see #endsEach
     * @see #endsAll
     * @see #describes
     * @see #fshould
     * @see #fshouldThrow
     * @see #fdescribes
     * @see #xshould
     * @see #xshouldThrow
     * @see #xdescribes
     */
    <T extends Throwable> ThrowableExpectations<T> shouldThrow(Class<T> expectedException,
            String description, TestClosure testBody);

    /**
     * A lifecycle callback executed after each test case.
     * The equivalent of RSpec's or Jasmine's {@code afterEach}.
     * <p>
     * You can have any number of them in each group,
     * and they are guaranteed to run in the order they were declared.
     * <p>
     * Nested groups execute all of their ancestor's {@code endsEach} callbacks.
     * They are executed in "inside-out" order -
     * meaning, child ones run before the parent's
     * (in opposite order than the {@code beforeEach} ones).
     *
     * @param closure
     *     the body of the callback
     *
     * @see #should
     * @see #shouldThrow
     * @see #beginsEach
     * @see #beginsAll
     * @see #endsAll
     * @see #describes
     * @see #fshould
     * @see #fshouldThrow
     * @see #fdescribes
     * @see #xshould
     * @see #xshouldThrow
     * @see #xdescribes
     */
    void endsEach(TestClosure closure);

    /**
     * A lifecycle callback executed once after the test group it was declared in.
     * The equivalent of RSpec's or Jasmine's {@code afterAll}.
     * <p>
     * You can have any number of them in each group,
     * and they are guaranteed to run in the order they were declared.
     * <p>
     * Nested groups execute all of their ancestor's {@code endsAll} callbacks.
     * They are executed in "inside-out" order -
     * meaning, child ones run before the parent's
     * (in opposite order than the {@code beforeAll} ones).
     *
     * @param closure
     *     the body of the callback
     *
     * @see #should
     * @see #shouldThrow
     * @see #beginsEach
     * @see #beginsAll
     * @see #endsEach
     * @see #describes
     * @see #fshould
     * @see #fshouldThrow
     * @see #fdescribes
     * @see #xshould
     * @see #xshouldThrow
     * @see #xdescribes
     */
    void endsAll(TestClosure closure);

    /**
     * Creates a subgroup of tests, with the current group as it's parent -
     * a specification within the specification.
     * The subgroup can use all of the same methods
     * ({@code begins/ends/should/describes}) that the parent group has.
     * <p>
     * You can have as many {@code describes} in a test group as you want,
     * and you can nest them arbitrarily deep.
     *
     * @param description
     *    the description of the new test group
     * @param specClosure
     *    the callback used to define the new test group,
     *    similarly to how {@link Specnaz#describes} defines the
     *    top-level group
     *
     * @see #should
     * @see #shouldThrow
     * @see #beginsEach
     * @see #beginsAll
     * @see #endsEach
     * @see #endsAll
     * @see #fshould
     * @see #fshouldThrow
     * @see #fdescribes
     * @see #xshould
     * @see #xshouldThrow
     * @see #xdescribes
     */
    void describes(String description, Runnable specClosure);

    /**
     * Used to temporarily mark a {@link #should} test as 'focused'.
     * <p>
     * This feature works the same as in <a href="http://rspec.info/">RSpec</a>
     * or <a href="http://jasmine.github.io/">Jasmine</a>.
     * If a class contains at least one focused test,
     * then only focused tests will be ran when it is executed -
     * unfocused (that is, created with {@link #should}) tests will be ignored.
     * This is useful when wanting to run and debug a single test in a class -
     * it's easy to run a single test when using 'vanilla' JUnit, but quite hard with Specnaz
     * (the IDEs and build tools were not really designed for tree-based tests).
     * With this method, you can simply add an 'f' in front of a call to {@link #should},
     * and the next time this spec class is ran,
     * only the {@code fshould} tests will actually be executed.
     * <p>
     * Naturally, all of the fixtures ({@code beginsAll/Each} and
     * {@code endsAll/Each}) in the tree will be executed,
     * just like for regular, 'unfocused' tests -
     * including fixtures from parent groups whose tests were all ignored
     * because of not being focused.
     *
     * @param description
     *     the description of this test.
     *     It will serve as the name for this test in the reports.
     *     Note that the word 'should' will be prepended to this description
     *     by the library
     * @param testBody
     *     the body of the test case
     *
     * @deprecated
     *     This method is deprecated, as it's only meant as a temporary stop
     *     gap to aid you in debugging a failing test - it's not meant to be
     *     part of the test suite permanently. Deprecating it means
     *     there is a higher chance you notice it, and remember to remove the 'f'
     *     at the beginning before committing the change to source control.
     * @return
     *     an instance of the {@link TestSettings} class
     *
     * @see #should
     * @see #shouldThrow
     * @see #beginsEach
     * @see #beginsAll
     * @see #endsEach
     * @see #endsAll
     * @see #describes
     * @see #fshouldThrow
     * @see #fdescribes
     * @see #xshould
     * @see #xshouldThrow
     * @see #xdescribes
     */
    @Deprecated
    TestSettings fshould(String description, TestClosure testBody);

    /**
     * The 'focused' equivalent of {@link #shouldThrow}.
     * See the {@link #fshould} documentation for a description of
     * what it means to be 'focused'.
     *
     * @param expectedException
     *     the type of Exception expected from the test.
     *     The Exception resulting from executing the test must be of this type
     *     (so, be an instance of either the {@code expectedException} class,
     *     or a subclass of {@code expectedException})
     *     for the test to have been deemed passing
     * @param description
     *     the description of this test.
     *     It will serve as the name for this test in the reports.
     *     Note that the words 'should throw &lt;ExpectedExceptionClass&gt;'
     *     will be prepended to it by the library
     * @param testBody
     *     the body of the test case
     * @return
     *     a new instance of the {@link ThrowableExpectations} class
     *
     * @deprecated
     *     This method is deprecated for exactly the same reasons {@link #fshould} is.
     *
     * @see #should
     * @see #shouldThrow
     * @see #beginsEach
     * @see #beginsAll
     * @see #endsEach
     * @see #endsAll
     * @see #describes
     * @see #fshould
     * @see #fdescribes
     * @see #xshould
     * @see #xshouldThrow
     * @see #xdescribes
     */
    @Deprecated
    <T extends Throwable> ThrowableExpectations<T> fshouldThrow(Class<T> expectedException,
            String description, TestClosure testBody);

    /**
     * The 'focused' equivalent of {@link #describes}.
     * See the {@link #fshould} documentation for a description of
     * what it means to be 'focused'.
     * Using this method, all of the tests present in this group will be focused,
     * even if they were defined with regular {@link #should} calls instead of
     * {@link #fshould}.
     *
     * @param description
     *    the description of the test group
     * @param specClosure
     *    the callback used to define the new test group,
     *    similarly to how {@link Specnaz#describes} defines the
     *    top-level group
     *
     * @deprecated
     *     This method is deprecated for exactly the same reasons {@link #fshould} is.
     *
     * @see #should
     * @see #shouldThrow
     * @see #beginsEach
     * @see #beginsAll
     * @see #endsEach
     * @see #endsAll
     * @see #describes
     * @see #fshould
     * @see #fshouldThrow
     * @see #xshould
     * @see #xshouldThrow
     * @see #xdescribes
     */
    @Deprecated
    void fdescribes(String description, Runnable specClosure);

    /**
     * Allows you to ignore a test.
     * <p>
     * This works like in RSpec or Jasmine.
     * If you ever need to ignore a test for any reason,
     * simply add an 'x' in front of the call to {@link #should},
     * and it will be skipped (just like if you placed an
     * {@code @Ignore} annotation on a JUnit test).
     *
     * @param description
     *     the description of this test.
     *     It will serve as the name for this test in the reports.
     *     Note that the word 'should' will be prepended to this description
     *     by the library
     * @param testBody
     *     the body of the test case (will not be executed)
     * @return
     *     an instance of the {@link TestSettings} class
     *
     * @see #should
     * @see #shouldThrow
     * @see #beginsEach
     * @see #beginsAll
     * @see #endsEach
     * @see #endsAll
     * @see #describes
     * @see #fshould
     * @see #fshouldThrow
     * @see #fdescribes
     * @see #xshouldThrow
     * @see #xdescribes
     */
    TestSettings xshould(String description, TestClosure testBody);

    /**
     * Allows you to ignore a test.
     * <p>
     * This works like in RSpec or Jasmine.
     * If you ever need to ignore a test for any reason,
     * simply add an 'x' in front of the call to {@link #shouldThrow},
     * and it will be skipped (just like if you placed an
     * {@code @Ignore} annotation on a JUnit test).
     *
     * @param expectedException
     *    the class of the expected Exception (will never be used in this case)
     * @param description
     *     the description of this test.
     *     It will serve as the name for this test in the reports.
     *     Note that the words 'should throw &lt;ExpectedExceptionClass&gt;'
     *     will be prepended to it by the library
     * @param testBody
     *     the body of the test case (will not be executed)
     * @return
     *     a new instance of the {@link ThrowableExpectations} class
     *
     * @see #should
     * @see #shouldThrow
     * @see #beginsEach
     * @see #beginsAll
     * @see #endsEach
     * @see #endsAll
     * @see #describes
     * @see #fshould
     * @see #fshouldThrow
     * @see #fdescribes
     * @see #xshould
     * @see #xdescribes
     */
    <T extends Throwable> ThrowableExpectations<T> xshouldThrow(Class<T> expectedException,
            String description, TestClosure testBody);

    /**
     * Allows you to ignore an entire group of tests.
     * <p>
     * This works like in RSpec or Jasmine.
     * If you ever need to ignore a whole subtree of tests for any reason,
     * simply add an 'x' in front of the call to {@link #describes},
     * and all of the tests in that group (including any child groups)
     * will be skipped.
     *
     * @param description
     *    the description of the skipped test group
     * @param specClosure
     *    the callback used to define the new (ignored) test group,
     *    similarly to how {@link Specnaz#describes} defines the
     *    top-level group
     *
     * @see #should
     * @see #shouldThrow
     * @see #beginsEach
     * @see #beginsAll
     * @see #endsEach
     * @see #endsAll
     * @see #describes
     * @see #fshould
     * @see #fshouldThrow
     * @see #fdescribes
     * @see #xshould
     * @see #xshouldThrow
     */
    void xdescribes(String description, Runnable specClosure);
}
