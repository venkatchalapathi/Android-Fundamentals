package com.venkat.kotlin_programming.functions

/**
 * ===================================
 * 4. EXTENSION FUNCTIONS
 * ===================================
 * Extension functions allow you to add new functions to existing classes
 * without modifying their source code or inheriting from them.
 * 
 * Syntax: fun ClassName.functionName() { }
 */

fun main() {
    demonstrateExtensionFunctions()
}
// Extension function for String
fun String.addExclamation(): String {
    return "$this!"
}

// Extension function with parameter
fun String.repeat(times: Int): String {
//    return this.repeat(times)
    return this
}

// Extension function for Int
fun Int.isEven(): Boolean {
    return this % 2 == 0
}

fun Int.isOdd(): Boolean {
    return this % 2 != 0
}

// Extension function for List
fun List<Int>.average(): Double {
    return if (this.isNotEmpty()) this.sum().toDouble() / this.size else 0.0
}

// Extension function with generic type
fun <T> List<T>.secondOrNull(): T? {
    return if (this.size >= 2) this[1] else null
}

// Extension property (computed property)
val String.wordCount: Int
    get() = this.split("\\s+".toRegex()).size

val Int.squared: Int
    get() = this * this

// Extension function for custom class
data class Student(val name: String, val marks: List<Int>)

fun Student.hasPassed(): Boolean {
    val average = if (marks.isNotEmpty()) marks.sum() / marks.size else 0
    return average >= 40
}

fun Student.getGrade(): String {
    val average = if (marks.isNotEmpty()) marks.sum() / marks.size else 0
    return when {
        average >= 90 -> "A+"
        average >= 80 -> "A"
        average >= 70 -> "B"
        average >= 60 -> "C"
        average >= 40 -> "D"
        else -> "F"
    }
}

/**
 * Nullable Extension Functions
 */
fun String?.isNullOrEmpty(): Boolean {
    return this == null || this.isEmpty()
}

fun Int?.orZero(): Int {
    return this ?: 0
}

/**
 * Usage Examples:
 */
fun demonstrateExtensionFunctions() {
    println("\n=== EXTENSION FUNCTIONS ===")
    
    // String extensions
    val message = "Hello"
    println(message.addExclamation())
    println("Ha".repeat(3))
    
    // Int extensions
    val number = 8
    println("$number is even: ${number.isEven()}")
    println("7 is odd: ${7.isOdd()}")
    
    // Extension property
    println("$number squared: ${number.squared}")
    
    // List extensions
    val numbers = listOf(10, 20, 30, 40, 50)
    println("Average: ${numbers.average()}")
    println("Second element: ${numbers.secondOrNull()}")
    
    val fruits = listOf("apple", "banana", "cherry")
    println("Second fruit: ${fruits.first()}")
    
    // String extension property
    val sentence = "This is a sentence with several words"
    println("Word count: ${sentence.wordCount}")
    
    // Custom class extensions
    val student1 = Student("Alice", listOf(85, 90, 88))
    val student2 = Student("Bob", listOf(35, 40, 32))
    
    println("${student1.name} passed: ${student1.hasPassed()}, Grade: ${student1.getGrade()}")
    println("${student2.name} passed: ${student2.hasPassed()}, Grade: ${student2.getGrade()}")
    
    // Nullable extensions
    val nullableString: String? = null
    println("Is null or empty: ${nullableString.isNullOrEmpty()}")
    
    val nullableInt: Int? = null
    println("Value or zero: ${nullableInt.orZero()}")
}

