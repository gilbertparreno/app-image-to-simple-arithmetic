package com.gilbertparreno.exam

import com.gilbertparreno.exam.core.extensions.getFirstArithmeticExpression
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun testMultipleExpression() {
        val test = "hello world110.5+300.75macaroni1+1".getFirstArithmeticExpression()
        assert(test == "110.5+300.75")
    }

    @Test
    fun testSingleExpression() {
        val test = "hello worldmacaroni1+ 1".getFirstArithmeticExpression()
        assert(test == "1+1")
    }

    @Test
    fun testNoExpression() {
        val test = "hello worldmacaroni".getFirstArithmeticExpression()
        assert(test == null)
    }
}