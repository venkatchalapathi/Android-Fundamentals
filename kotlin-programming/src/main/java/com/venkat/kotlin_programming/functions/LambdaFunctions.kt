package com.venkat.kotlin_programming.functions

/**
 * ===================================
 * 3. LAMBDA FUNCTIONS
 * ===================================
 * Lambda expressions are anonymous functions that can be treated as values.
 * They are heavily used in Kotlin for concise code.
 */

/**
 * Lambda Syntax:
 * { parameters -> body }
 * 
 * If lambda has single parameter, you can use 'it' instead of naming it
 */

// Lambda assigned to a variable
val square: (Int) -> Int = { number -> number * number }

// Lambda with single parameter using 'it'
val isEven: (Int) -> Boolean = { it % 2 == 0 }

// Lambda with multiple parameters
val concatenate: (String, String) -> String = { str1, str2 -> "$str1 $str2" }

// Lambda with no parameters
val getCurrentTime: () -> Long = { System.currentTimeMillis() }

// Lambda with multiple statements
val validatePassword: (String) -> Boolean = { password ->
    val hasMinLength = password.length >= 8
    val hasDigit = password.any { it.isDigit() }
    val hasLetter = password.any { it.isLetter() }
    hasMinLength && hasDigit && hasLetter
}

/**
 * Common use cases with collection operations
 */
fun demonstrateLambdaFunctions() {
    println("\n=== LAMBDA FUNCTIONS ===")
    
    // Using lambda variables
    println("Square of 5: ${square(5)}")
    println("Is 4 even? ${isEven(4)}")
    println("Concatenate: ${concatenate("Hello", "World")}")
    println("Current time: ${getCurrentTime()}")
    println("Is 'Pass123' valid? ${validatePassword("Pass123")}")
    
    // Lambdas with collections
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    
    // map: transform each element
    val squared = numbers.map { it * it }
    println("\nSquared numbers: $squared")
    
    // filter: keep elements matching condition
    val evenNumbers = numbers.filter { it % 2 == 0 }
    println("Even numbers: $evenNumbers")
    
    // forEach: perform action on each element
    print("Numbers: ")
    numbers.forEach { print("$it ") }
    println()
    
    // any: check if any element matches
    val hasNumberGreaterThan5 = numbers.any { it > 5 }
    println("Has number > 5? $hasNumberGreaterThan5")
    
    // all: check if all elements match
    val allPositive = numbers.all { it > 0 }
    println("All positive? $allPositive")
    
    // find: get first matching element
    val firstEven = numbers.find { it % 2 == 0 }
    println("First even: $firstEven")
    
    // reduce: combine all elements
    val sum = numbers.reduce { acc, num -> acc + num }
    println("Sum using reduce: $sum")
    
    // fold: like reduce but with initial value
    val product = numbers.fold(1) { acc, num -> acc * num }
    println("Product using fold: $product")
    
    // groupBy: group elements by key
    val grouped = numbers.groupBy { if (it % 2 == 0) "even" else "odd" }
    println("Grouped: $grouped")
    
    // sortedBy: sort by custom criteria
    val strings = listOf("apple", "pie", "banana", "kiwi")
    val sortedByLength = strings.sortedBy { it.length }
    println("Sorted by length: $sortedByLength")
    
    // chaining operations
    val result = numbers
        .filter { it % 2 == 0 }
        .map { it * it }
        .take(3)
    println("Chained operations result: $result")
}

