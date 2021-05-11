package com.petamind.calculatorx.ui

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class Dummy2Test {
    var dummy2: Dummy2? = null
    @Before
    fun setUp() {
        dummy2 = Dummy2()
    }

    @Test
    fun add() {
        assert(dummy2?.add(1, 2.0) == 3.0)
    }

    @Test
    fun add1() {
        assertNotEquals(dummy2?.add(1, 2.0), 3)
    }
}