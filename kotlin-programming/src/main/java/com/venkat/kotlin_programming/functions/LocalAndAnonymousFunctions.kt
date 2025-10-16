package com.venkat.kotlin_programming.functions

/**
 * ===================================
 * 12. LOCAL FUNCTIONS
 * ===================================
 * Functions defined inside other functions.
 * They have access to the outer function's variables (closure).
 */

fun calculateStats(numbers: List<Int>): String {
    // Local function to calculate mean
    fun calculateMean(): Double {
        return if (numbers.isNotEmpty()) {
            numbers.sum().toDouble() / numbers.size
        } else 0.0
    }
    
    // Local function to calculate median
    fun calculateMedian(): Double {
        if (numbers.isEmpty()) return 0.0
        val sorted = numbers.sorted()
        val middle = sorted.size / 2
        return if (sorted.size % 2 == 0) {
            (sorted[middle - 1] + sorted[middle]) / 2.0
        } else {
            sorted[middle].toDouble()
        }
    }
    
    // Local function to calculate mode
    fun calculateMode(): Int? {
        if (numbers.isEmpty()) return null
        val frequency = numbers.groupingBy { it }.eachCount()
        val maxFrequency = frequency.maxByOrNull { it.value }?.value
        return frequency.filter { it.value == maxFrequency }.keys.firstOrNull()
    }
    
    val mean = calculateMean()
    val median = calculateMedian()
    val mode = calculateMode()
    
    return """
        Statistics:
        Mean: $mean
        Median: $median
        Mode: ${mode ?: "N/A"}
    """.trimIndent()
}

// Another example of local functions
fun validateUser(email: String, password: String): Pair<Boolean, String> {
    // Local validation functions
    fun isValidEmail(): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
        return email.matches(emailRegex)
    }
    
    fun isValidPassword(): Boolean {
        return password.length >= 8 && 
               password.any { it.isDigit() } &&
               password.any { it.isLetter() }
    }
    
    fun getErrorMessage(): String {
        return when {
            !isValidEmail() -> "Invalid email format"
            !isValidPassword() -> "Password must be at least 8 characters with letters and digits"
            else -> "Valid"
        }
    }
    
    val isValid = isValidEmail() && isValidPassword()
    return Pair(isValid, getErrorMessage())
}

/**
 * ===================================
 * 13. ANONYMOUS FUNCTIONS
 * ===================================
 * Functions without a name, alternative to lambda expressions.
 * Can specify return type explicitly and use return statement.
 */

fun demonstrateLocalAndAnonymousFunctions() {
    println("\n=== LOCAL FUNCTIONS ===")
    
    // Using local functions
    val numbers = listOf(10, 20, 30, 40, 50, 20, 30, 20)
    println(calculateStats(numbers))
    
    // Validation example
    val (isValid1, message1) = validateUser("test@example.com", "Pass1234")
    println("\nEmail: test@example.com, Password: Pass1234")
    println("Valid: $isValid1, Message: $message1")
    
    val (isValid2, message2) = validateUser("invalid-email", "short")
    println("\nEmail: invalid-email, Password: short")
    println("Valid: $isValid2, Message: $message2")
    
    println("\n=== ANONYMOUS FUNCTIONS ===")
    
    // Anonymous function (alternative to lambda)
    val square = fun(x: Int): Int {
        return x * x
    }
    println("Square of 5: ${square(5)}")
    
    // Anonymous function with explicit type
    val divide = fun(a: Int, b: Int): Double {
        if (b == 0) {
            println("Cannot divide by zero")
            return 0.0
        }
        return a.toDouble() / b
    }
    println("10 / 3 = ${divide(10, 3)}")
    println("10 / 0 = ${divide(10, 0)}")
    
    // Using anonymous function with higher-order function
    val nums = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    
    val evenNumbers = nums.filter(fun(num: Int): Boolean {
        return num % 2 == 0
    })
    println("\nEven numbers: $evenNumbers")
    
    // Anonymous function allows non-local return
    val result = nums.map(fun(num: Int): Int {
        if (num == 5) return 0  // Returns from anonymous function
        return num * 2
    })
    println("Mapped with early return: $result")
    
    // Difference: Lambda doesn't allow non-local return
    println("\n=== LAMBDA vs ANONYMOUS FUNCTION ===")
    
    // Lambda syntax
    val lambdaFunc = { x: Int -> x * x }
    println("Lambda: ${lambdaFunc(4)}")
    
    // Anonymous function syntax
    val anonFunc = fun(x: Int): Int { return x * x }
    println("Anonymous function: ${anonFunc(4)}")
    
    println("\nKey differences:")
    println("1. Lambda: { params -> expression }")
    println("2. Anonymous: fun(params): ReturnType { body }")
    println("3. Lambda: return exits outer function")
    println("4. Anonymous: return exits only the anonymous function")
    println("5. Anonymous function can have explicit return type")
}

