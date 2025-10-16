package com.venkat.kotlin_programming.functions

/**
 * ===================================
 * 5. INFIX FUNCTIONS
 * ===================================
 * Infix functions allow you to call functions without using dots and parentheses.
 * Requirements:
 * - Must be member functions or extension functions
 * - Must have single parameter
 * - Must be marked with 'infix' keyword
 */

// Infix function for Int
infix fun Int.times(str: String): String {
    return str.repeat(this)
}

// Infix function for custom operations
infix fun Int.pow(exponent: Int): Int {
    var result = 1
    repeat(exponent) {
        result *= this
    }
    return result
}

// Infix function for String
infix fun String.concat(other: String): String {
    return "$this $other"
}

// Practical example: DSL-style infix functions
data class Point(val x: Int, val y: Int)

infix fun Point.moveTo(direction: String): Point {
    return when (direction.lowercase()) {
        "up" -> Point(x, y + 1)
        "down" -> Point(x, y - 1)
        "left" -> Point(x - 1, y)
        "right" -> Point(x + 1, y)
        else -> this
    }
}

/**
 * ===================================
 * 6. OPERATOR FUNCTIONS
 * ===================================
 * Operator overloading allows you to use operators (+, -, *, /, etc.)
 * with custom types by marking functions with 'operator' keyword.
 */

data class Vector(val x: Int, val y: Int) {
    // Overload + operator
    operator fun plus(other: Vector): Vector {
        return Vector(x + other.x, y + other.y)
    }
    
    // Overload - operator
    operator fun minus(other: Vector): Vector {
        return Vector(x - other.x, y - other.y)
    }
    
    // Overload * operator (scalar multiplication)
    operator fun times(scalar: Int): Vector {
        return Vector(x * scalar, y * scalar)
    }
    
    // Overload unary minus
    operator fun unaryMinus(): Vector {
        return Vector(-x, -y)
    }
    
    // Overload [] operator (get)
    operator fun get(index: Int): Int {
        return when (index) {
            0 -> x
            1 -> y
            else -> throw IndexOutOfBoundsException("Vector has only 2 elements")
        }
    }
}

// Overloading operators for existing types
operator fun String.times(n: Int): String {
    return this.repeat(n)
}

// Range operator
data class DateRange(val start: Int, val end: Int) {
    operator fun contains(value: Int): Boolean {
        return value in start..end
    }
}

/**
 * ===================================
 * 7. INVOKE OPERATOR
 * ===================================
 * Makes objects callable like functions
 */
class Greeter(val greeting: String) {
    operator fun invoke(name: String) {
        println("$greeting, $name!")
    }
}

/**
 * Usage Examples:
 */
fun demonstrateInfixAndOperatorFunctions() {
    println("\n=== INFIX FUNCTIONS ===")
    
    // Using infix functions (without dots and parentheses)
    val repeated = 3 times "Ha"
    println("3 times Ha: $repeated")
    
    val power = 2 pow 8
    println("2 to the power 8: $power")
    
    val concatenated = "Hello" concat "World"
    println("Concatenated: $concatenated")
    
    // Point movement with infix
    val point = Point(0, 0)
    val moved = point moveTo "up" moveTo "right" moveTo "right"
    println("Point moved: $moved")
    
    println("\n=== OPERATOR FUNCTIONS ===")
    
    // Using overloaded operators
    val v1 = Vector(3, 4)
    val v2 = Vector(1, 2)
    
    val sum = v1 + v2
    println("v1 + v2 = $sum")
    
    val diff = v1 - v2
    println("v1 - v2 = $diff")
    
    val scaled = v1 * 3
    println("v1 * 3 = $scaled")
    
    val negated = -v1
    println("-v1 = $negated")
    
    println("v1[0] = ${v1[0]}, v1[1] = ${v1[1]}")
    
    // String * Int
    val repeatedStr = "Hello" * 3
    println("Hello * 3 = $repeatedStr")
    
    // Contains operator
    val dateRange = DateRange(1, 31)
    println("15 in range: ${15 in dateRange}")
    println("35 in range: ${35 in dateRange}")
    
    println("\n=== INVOKE OPERATOR ===")
    
    // Using invoke operator
    val greeter = Greeter("Good morning")
    greeter("Alice")  // Calling object like a function
}

