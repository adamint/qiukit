package com.adamratzman.qiukit

import org.apache.commons.math3.complex.Complex
import kotlin.math.abs
import kotlin.math.atan
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random

data class Qubit(val zero: QubitAmplitude, val one: QubitAmplitude, val random: Random = Random) {
    val phase: Double get() = zero.phase - one.phase

    init {
    }

    fun not() = Not().evaluate(this)
    fun read() = Read().evaluate(this)

    enum class State {
        ONE, ZERO
    }

    companion object {
        val zero = getZero()
        val one = getOne()

        fun getZero(random: Random = Random) = Qubit(
            QubitAmplitude(Complex(1.0)),
            QubitAmplitude(Complex(0.0)),
            random
        )

        fun getOne(random: Random = Random) = Qubit(
            QubitAmplitude(Complex(0.0)),
            QubitAmplitude(Complex(1.0)),
            random
        )
    }
}

/**
 * @property phase the relative phase shift of the qubit in radians
 */
data class QubitAmplitude(val amplitude: Complex) {
    val magnitude get() = sqrt(abs(amplitude.real.pow(2) + amplitude.imaginary.pow(2)))
    val probability = magnitude.pow(2)
    val phase: Double
        get() =
            if (amplitude.real >= 0) atan(amplitude.imaginary / amplitude.real)
            else atan(amplitude.imaginary / amplitude.real) + Math.PI

}