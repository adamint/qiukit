package com.adamratzman.qiukit

import org.apache.commons.math3.complex.Complex
import org.junit.Assert
import org.junit.Test

internal class NotTest {
    @Test
    fun evaluate() {
        val not = Not()
        val qubit = Qubit.one

        val result = not.evaluate(qubit)

        Assert.assertEquals(Qubit.zero, result)
    }
}