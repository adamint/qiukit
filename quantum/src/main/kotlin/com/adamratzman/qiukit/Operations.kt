package com.adamratzman.qiukit

interface QubitOperation

interface QubitUnaryOperation <T> : QubitOperation {
    fun evaluate(argument: T): Qubit
}

interface QubitBinaryOperation <A, B>: QubitOperation {
    fun evaluate(first: A, second: B): Qubit
}