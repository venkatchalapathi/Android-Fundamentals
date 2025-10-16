package com.venkat.kotlin_programming.functions

/**
 * ===================================
 * 2. HIGHER-ORDER FUNCTIONS
 * ===================================
 * Functions that take other functions as parameters or return functions.
 * This is a powerful feature in Kotlin for functional programming.
 */

// Function that takes another function as parameter
fun calculate(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}

// Function that returns a function
fun getOperation(operationType: String): (Int, Int) -> Int {
    return when (operationType) {
        "add" -> { x, y -> x + y }
        "subtract" -> { x, y -> x - y }
        "multiply" -> { x, y -> x * y }
        "divide" -> { x, y -> if (y != 0) x / y else 0 }
        else -> { x, y -> 0 }
    }
}

// Function with function type parameter and default value
fun processNumbers(
    numbers: List<Int>,
    filter: (Int) -> Boolean = { it > 0 }
): List<Int> {
    return numbers.filter(filter)
}

// Function with multiple function parameters
fun transformAndProcess(
    value: Int,
    transform: (Int) -> Int,
    process: (Int) -> Unit
) {
    val transformed = transform(value)
    process(transformed)
}

/**
 * Usage Examples:
 */
fun demonstrateHigherOrderFunctions() {
    println("\n=== HIGHER-ORDER FUNCTIONS ===")
    
    // Using function as parameter
    val result1 = calculate(10, 5) { x, y -> x + y }
    println("10 + 5 = $result1")
    
    val result2 = calculate(10, 5) { x, y -> x * y }
    println("10 * 5 = $result2")
    
    // Getting function from function
    val addFunc = getOperation("add")
    println("Using returned function: ${addFunc(7, 3)}")
    
    val multiplyFunc = getOperation("multiply")
    println("Using returned function: ${multiplyFunc(7, 3)}")
    
    // Processing with filter
    val numbers = listOf(-2, 3, -1, 5, 0, 8)
    val positive = processNumbers(numbers)
    println("Positive numbers: $positive")
    
    val evenNumbers = processNumbers(numbers) { it % 2 == 0 }
    println("Even numbers: $evenNumbers")
    
    // Transform and process
    transformAndProcess(
        value = 5,
        transform = { it * it },
        process = { println("Squared value: $it") }
    )
}

