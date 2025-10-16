package com.venkat.kotlin_programming.functions

/**
 * ===================================
 * 10. TAIL RECURSIVE FUNCTIONS
 * ===================================
 * Tail recursion is when the recursive call is the last operation in the function.
 * Kotlin can optimize these with the 'tailrec' modifier to prevent stack overflow.
 */

// Regular recursive function (can cause stack overflow for large n)
fun factorial(n: Int): Long {
    return if (n <= 1) 1 else n * factorial(n - 1)
}

// Tail recursive function (optimized by Kotlin compiler)
tailrec fun factorialTailRec(n: Int, accumulator: Long = 1): Long {
    return if (n <= 1) accumulator else factorialTailRec(n - 1, n * accumulator)
}

// Tail recursive function for calculating Fibonacci
tailrec fun fibonacci(n: Int, a: Long = 0, b: Long = 1): Long {
    return when (n) {
        0 -> a
        1 -> b
        else -> fibonacci(n - 1, b, a + b)
    }
}

// Tail recursive function to find greatest common divisor
tailrec fun gcd(a: Int, b: Int): Int {
    return if (b == 0) a else gcd(b, a % b)
}

// Tail recursive function to reverse a list
tailrec fun <T> reverseList(
    list: List<T>,
    accumulator: List<T> = emptyList()
): List<T> {
    return if (list.isEmpty()) {
        accumulator
    } else {
        reverseList(list.drop(1), listOf(list.first()) + accumulator)
    }
}

/**
 * ===================================
 * 11. GENERATOR FUNCTIONS (SEQUENCES)
 * ===================================
 * Kotlin doesn't have 'yield' like Python, but uses Sequences for lazy evaluation.
 * Sequences are evaluated lazily - elements are computed on demand.
 */

// Generate infinite sequence
fun generateInfiniteNumbers(): Sequence<Int> = sequence {
    var num = 0
    while (true) {
        yield(num++)
    }
}

// Generate Fibonacci sequence
fun fibonacciSequence(): Sequence<Long> = sequence {
    var a = 0L
    var b = 1L
    yield(a)
    yield(b)
    while (true) {
        val next = a + b
        yield(next)
        a = b
        b = next
    }
}

// Generate sequence with custom logic
fun generatePrimes(): Sequence<Int> = sequence {
    yield(2)
    var num = 3
    while (true) {
        if (isPrime(num)) {
            yield(num)
        }
        num += 2  // Only check odd numbers
    }
}

fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    val sqrt = kotlin.math.sqrt(n.toDouble()).toInt()
    for (i in 3..sqrt step 2) {
        if (n % i == 0) return false
    }
    return true
}

// Practical sequence example - data processing
fun processLargeDataset(): Sequence<String> = sequence {
    // Simulating large dataset processing
    for (i in 1..1000000) {
        val processed = "Item-$i-processed"
        yield(processed)
    }
}

/**
 * Usage Examples:
 */
fun demonstrateTailRecursiveAndGeneratorFunctions() {
    println("\n=== TAIL RECURSIVE FUNCTIONS ===")
    
    // Factorial comparison
    println("Factorial of 10: ${factorial(10)}")
    println("Factorial (tail-rec) of 10: ${factorialTailRec(10)}")
    println("Factorial (tail-rec) of 20: ${factorialTailRec(20)}")
    
    // Fibonacci
    println("\nFibonacci numbers:")
    for (i in 0..10) {
        print("${fibonacci(i)} ")
    }
    println()
    
    // GCD
    println("\nGCD of 48 and 18: ${gcd(48, 18)}")
    println("GCD of 100 and 35: ${gcd(100, 35)}")
    
    // Reverse list
    val list = listOf(1, 2, 3, 4, 5)
    println("\nOriginal list: $list")
    println("Reversed list: ${reverseList(list)}")
    
    println("\n=== GENERATOR FUNCTIONS (SEQUENCES) ===")
    
    // Infinite sequence (take only what you need)
    println("\nFirst 10 numbers:")
    val first10 = generateInfiniteNumbers().take(10).toList()
    println(first10)
    
    // Fibonacci sequence
    println("\nFirst 15 Fibonacci numbers:")
    val fib15 = fibonacciSequence().take(15).toList()
    println(fib15)
    
    // Prime numbers
    println("\nFirst 10 prime numbers:")
    val primes10 = generatePrimes().take(10).toList()
    println(primes10)
    
    // Lazy evaluation advantage
    println("\n=== LAZY EVALUATION DEMONSTRATION ===")
    
    // Sequences are lazy - only computed when needed
    println("\nCreating sequence (no computation yet)...")
    val lazySequence = (1..1000000).asSequence()
        .map { 
            println("Mapping $it")  // This won't print until we consume
            it * 2 
        }
        .filter { it > 100 }
    
    println("Taking first 3 elements...")
    val first3 = lazySequence.take(3).toList()
    println("Result: $first3")
    
    // Compare with eager evaluation (List)
    println("\n=== SEQUENCE vs LIST PERFORMANCE ===")
    
    // Sequence (lazy)
    val sequenceTime = measureTimeMillis {
        val result = (1..1000000).asSequence()
            .filter { it % 2 == 0 }
            .map { it * it }
            .take(10)
            .toList()
    }
    println("Sequence time: ${sequenceTime}ms")
    
    // List (eager)
    val listTime = measureTimeMillis {
        val result = (1..1000000)
            .filter { it % 2 == 0 }
            .map { it * it }
            .take(10)
    }
    println("List time: ${listTime}ms")
    
    println("\nNote: Sequences are more efficient when:")
    println("- Working with large datasets")
    println("- Only need partial results")
    println("- Chaining multiple operations")
}

fun measureTimeMillis(block: () -> Unit): Long {
    val start = System.currentTimeMillis()
    block()
    return System.currentTimeMillis() - start
}

