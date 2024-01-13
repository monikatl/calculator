package com.example.calculator

import kotlin.math.pow

abstract class Operation {
    val a = 40
    val b = 80
    abstract fun calculate(a: Double, b: Double): Double
}

class Add: Operation() {
    override fun calculate(a: Double, b: Double): Double {
        return a.plus(b)
    }
}

class Sub: Operation() {
    override fun calculate(a: Double, b: Double): Double {
        return a.minus(b)
    }
}

class Mul: Operation() {
    override fun calculate(a: Double, b: Double): Double {
        return a*b
    }
}

class Div: Operation() {
    override fun calculate(a: Double, b: Double): Double {
        return a/b
    }
}

class Pow: Operation() {
    override fun calculate(a: Double, b: Double): Double {
        return a.pow(b)
    }
}
