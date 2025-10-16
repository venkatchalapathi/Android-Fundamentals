package com.venkat.kotlin_programming.functions

/**
 * ===================================
 * 1. BASIC FUNCTIONS IN KOTLIN
 * ===================================
 * Functions are blocks of reusable code that perform specific tasks.
 */

fun main() {
    var a = sum(1,3,4,5,6,7,8,9,10)
    println(a)
}

// Simple function with no parameters and no return value
fun greet() {
    println("Hello, Kotlin!")
}

// Function with parameters
fun greetPerson(name: String) {
    println("Hello, $name!")
}

// Function with return type
fun add(a: Int, b: Int): Int {
    return a + b
}

// Single-expression function (concise syntax)
fun multiply(a: Int, b: Int): Int = a * b

// Function with default parameters
fun greetWithMessage(name: String, message: String = "Welcome!") {
    println("$message, $name")
}

// Function with named arguments (called like: calculateArea(width = 5, height = 10))
fun calculateArea(width: Int, height: Int): Int {
    return width * height
}

// Function with variable number of arguments (vararg)
fun sum(vararg numbers: Int): Int {
    var total = 0
    for (number in numbers) {
        total += number
    }
    return total
}

// Function returning multiple values using Pair
fun getMinMax(numbers: List<Int>): Pair<Int, Int> {
    return Pair(numbers.minOrNull() ?: 0, numbers.maxOrNull() ?: 0)
}

// Function returning multiple values using data class
data class Person(val name: String, val age: Int)
fun createPerson(): Person {
    return Person("John", 25)
}

// Function with nullable return type
fun findUser(id: Int): String? {
    return if (id > 0) "User$id" else null
}

/**
 * Usage Examples:
 */
fun demonstrateBasicFunctions() {
    println("\n=== BASIC FUNCTIONS ===")
    
    greet()
    greetPerson("Alice")
    
    val sum = add(5, 3)
    println("5 + 3 = $sum")
    
    val product = multiply(4, 6)
    println("4 * 6 = $product")
    
    greetWithMessage("Bob")
    greetWithMessage("Charlie", "Good Morning")
    
    val area = calculateArea(width = 5, height = 10)
    println("Area: $area")
    
    val total = sum(1, 2, 3, 4, 5)
    println("Sum: $total")
    
    val (min, max) = getMinMax(listOf(3, 7, 1, 9, 4))
    println("Min: $min, Max: $max")
    
    val person = createPerson()
    println("Person: ${person.name}, Age: ${person.age}")
    
    val user = findUser(1)
    println("User: ${user ?: "Not found"}")
}

