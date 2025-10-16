package com.venkat.kotlin_programming.functions

/**
 * ===================================
 * 8. INLINE FUNCTIONS
 * ===================================
 * Inline functions improve performance by copying the function's bytecode
 * to the call site instead of creating function objects.
 * Useful for higher-order functions to avoid overhead.
 */

inline fun measureTime(block: () -> Unit): Long {
    val start = System.currentTimeMillis()
    block()
    val end = System.currentTimeMillis()
    return end - start
}

// Inline function with reified type parameter
// Allows you to access type information at runtime
inline fun <reified T> isOfType(value: Any): Boolean {
    return value is T
}

/**
 * ===================================
 * 9. SCOPE FUNCTIONS
 * ===================================
 * Kotlin's standard library includes several functions that execute
 * a block of code within the context of an object.
 * 
 * The 5 scope functions: let, run, with, apply, also
 */

data class UserProfile(
    var name: String = "",
    var email: String = "",
    var age: Int = 0
)

fun demonstrateInlineAndScopeFunctions() {
    println("\n=== INLINE FUNCTIONS ===")
    
    // Using inline function
    val time = measureTime {
        var sum = 0
        for (i in 1..1000000) {
            sum += i
        }
        println("Sum calculated: $sum")
    }
    println("Time taken: ${time}ms")
    
    // Using reified type parameter
    println("Is 42 an Int? ${isOfType<Int>(42)}")
    println("Is 'Hello' an Int? ${isOfType<Int>("Hello")}")
    println("Is 'Hello' a String? ${isOfType<String>("Hello")}")
    
    println("\n=== SCOPE FUNCTIONS ===")
    
    /**
     * 1. let
     * - Context object: 'it'
     * - Return value: lambda result
     * - Use case: Execute code on nullable objects, transform result
     */
    val name: String? = "Alice"
    name?.let {
        println("1. let - Name length: ${it.length}")
        println("   Name uppercase: ${it.uppercase()}")
    }
    
    val numbers = listOf(1, 2, 3, 4, 5)
    val doubled = numbers.let {
        println("   Original: $it")
        it.map { num -> num * 2 }
    }
    println("   Doubled: $doubled")
    
    /**
     * 2. run
     * - Context object: 'this'
     * - Return value: lambda result
     * - Use case: Object configuration and computing result
     */
    val result = "Hello".run {
        println("\n2. run - Length: $length")
        println("   First char: ${first()}")
        uppercase()  // Return value
    }
    println("   Result: $result")
    
    /**
     * 3. with
     * - Not an extension function (takes object as parameter)
     * - Context object: 'this'
     * - Return value: lambda result
     * - Use case: Group function calls on an object
     */
    val stringBuilder = StringBuilder()
    val message = with(stringBuilder) {
        append("Hello")
        append(" ")
        append("World")
        toString()
    }
    println("\n3. with - Message: $message")
    
    /**
     * 4. apply
     * - Context object: 'this'
     * - Return value: the object itself
     * - Use case: Object configuration/initialization
     */
    val user = UserProfile().apply {
        this.name = "John Doe"
        this.email = "john@example.com"
        this.age = 30
    }
    println("\n4. apply - User: $user")
    
    /**
     * 5. also
     * - Context object: 'it'
     * - Return value: the object itself
     * - Use case: Additional actions, logging
     */
    val numbersList = mutableListOf(1, 2, 3)
        .also { println("\n5. also - Initial list: $it") }
        .also { it.add(4) }
        .also { println("   After adding 4: $it") }
        .also { it.removeAt(0) }
        .also { println("   After removing first: $it") }
    
    println("\n=== COMPARISON TABLE ===")
    println("Function | Context | Returns    | Use Case")
    println("---------|---------|------------|----------------------------------")
    println("let      | it      | Lambda     | Null safety, transformation")
    println("run      | this    | Lambda     | Object config + compute result")
    println("with     | this    | Lambda     | Group calls on object")
    println("apply    | this    | Object     | Object initialization")
    println("also     | it      | Object     | Additional actions, logging")
    
    println("\n=== PRACTICAL EXAMPLES ===")
    
    // Chaining scope functions
    val processedUser = UserProfile()
        .apply {
            this.name = "Alice"
            this.email = "alice@example.com"
        }
        .also { println("Created user: ${it.name}") }
        .let { user ->
            user.age = 25
            user
        }
        .run {
            println("Final user: name=$name, email=$email, age=$age")
            this
        }
    
    // Safe calls with let
    val nullableValue: String? = null
    nullableValue?.let {
        println("This won't print because value is null")
    } ?: println("Value is null, using elvis operator")
}

