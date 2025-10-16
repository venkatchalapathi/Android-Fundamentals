# Kotlin Functions - Complete Guide

## 📚 Table of Contents
1. [What are Functions?](#what-are-functions)
2. [Types of Functions](#types-of-functions)
3. [Where to Use Them](#where-to-use-them)
4. [Quick Reference](#quick-reference)

---

## What are Functions?

**Functions** are reusable blocks of code that perform specific tasks. They help you:
- **Organize code** into logical units
- **Reuse code** instead of repeating it
- **Improve readability** and maintainability
- **Test code** more easily

### Basic Syntax:
```kotlin
fun functionName(parameter: Type): ReturnType {
    // function body
    return value
}
```

---

## Types of Functions

### 1. **Basic Functions** 📝
Standard functions with parameters and return types.

**When to use:**
- General-purpose operations
- Data processing
- Calculations
- Business logic

**Example:**
```kotlin
fun add(a: Int, b: Int): Int = a + b
fun greet(name: String, message: String = "Hello") {
    println("$message, $name!")
}
```

---

### 2. **Higher-Order Functions** 🔝
Functions that take other functions as parameters or return functions.

**When to use:**
- Callback implementations
- Strategy pattern
- Custom behavior injection
- Event handlers

**Example:**
```kotlin
fun calculate(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}
// Usage: calculate(5, 3) { x, y -> x + y }
```

**Real-world use:**
- Button click listeners in Android
- API callbacks
- Data transformations
- Custom sorting/filtering logic

---

### 3. **Lambda Functions** λ
Anonymous functions with concise syntax.

**When to use:**
- Collection operations (map, filter, etc.)
- Short, simple operations
- Functional programming patterns
- Inline callbacks

**Example:**
```kotlin
val numbers = listOf(1, 2, 3, 4, 5)
val doubled = numbers.map { it * 2 }  // [2, 4, 6, 8, 10]
val evens = numbers.filter { it % 2 == 0 }  // [2, 4]
```

**Real-world use:**
- Processing lists of data
- RecyclerView adapters
- LiveData/Flow transformations
- Composable UI event handlers

---

### 4. **Extension Functions** ➕
Add new functions to existing classes without modifying them.

**When to use:**
- Add utility methods to standard classes
- Improve API readability
- Domain-specific language (DSL) creation
- Helper functions

**Example:**
```kotlin
fun String.addExclamation(): String = "$this!"
fun Int.isEven(): Boolean = this % 2 == 0

// Usage:
"Hello".addExclamation()  // "Hello!"
4.isEven()  // true
```

**Real-world use:**
- String utilities (formatting, validation)
- Date/Time helpers
- View extensions in Android
- Context extensions

---

### 5. **Infix Functions** ↔️
Call functions without dots and parentheses (cleaner syntax).

**When to use:**
- DSL creation
- Mathematical operations
- Readable API design
- Custom operators

**Example:**
```kotlin
infix fun Int.pow(exponent: Int): Int {
    return Math.pow(this.toDouble(), exponent.toDouble()).toInt()
}

// Usage:
val result = 2 pow 8  // 256 (instead of 2.pow(8))
```

**Real-world use:**
- Test assertions: `actual shouldBe expected`
- DSL for building objects
- Custom range operators

---

### 6. **Operator Functions** ➗
Overload operators (+, -, *, /, [], etc.) for custom types.

**When to use:**
- Mathematical types (Vector, Matrix)
- Custom collections
- Domain objects with natural operations
- DSL creation

**Example:**
```kotlin
data class Vector(val x: Int, val y: Int) {
    operator fun plus(other: Vector) = Vector(x + other.x, y + other.y)
}

// Usage:
val v1 = Vector(1, 2)
val v2 = Vector(3, 4)
val sum = v1 + v2  // Vector(4, 6)
```

**Real-world use:**
- Point/Vector math
- Money calculations
- Custom collections
- Game development

---

### 7. **Inline Functions** ⚡
Optimize higher-order functions by copying bytecode to call site.

**When to use:**
- Performance-critical lambdas
- Avoid object allocation
- Reified type parameters
- Small, frequently-called functions

**Example:**
```kotlin
inline fun measureTime(block: () -> Unit): Long {
    val start = System.currentTimeMillis()
    block()
    return System.currentTimeMillis() - start
}

inline fun <reified T> isOfType(value: Any): Boolean {
    return value is T
}
```

**Real-world use:**
- Performance utilities
- Logging wrappers
- Transaction blocks
- Generic type checking

---

### 8. **Scope Functions** 🎯
Execute code blocks within the context of an object.

| Function | Context | Returns | Use Case |
|----------|---------|---------|----------|
| `let` | it | Lambda result | Null safety, transformation |
| `run` | this | Lambda result | Object config + compute |
| `with` | this | Lambda result | Group calls on object |
| `apply` | this | Object itself | Object initialization |
| `also` | it | Object itself | Additional actions, logging |

**When to use:**
- Object configuration
- Null-safe operations
- Code organization
- Chaining operations

**Example:**
```kotlin
// let - null safety
val name: String? = getName()
name?.let {
    println("Name: $it")
}

// apply - object initialization
val person = Person().apply {
    name = "John"
    age = 30
}

// also - logging
val list = mutableListOf(1, 2, 3)
    .also { println("Before: $it") }
    .also { it.add(4) }
    .also { println("After: $it") }
```

**Real-world use:**
- View configuration in Android
- Builder pattern
- Safe navigation
- Debug logging

---

### 9. **Tail Recursive Functions** 🔄
Optimized recursive functions that won't cause stack overflow.

**When to use:**
- Recursive algorithms
- Large iteration counts
- Functional programming patterns
- Mathematical computations

**Example:**
```kotlin
tailrec fun factorial(n: Int, acc: Long = 1): Long {
    return if (n <= 1) acc else factorial(n - 1, n * acc)
}

// Usage:
factorial(20)  // Works without stack overflow
```

**Real-world use:**
- Tree traversal
- Factorial, Fibonacci
- File system operations
- Recursive data processing

---

### 10. **Generator Functions (Sequences)** 🌊
Lazy evaluation - elements computed on demand.

**When to use:**
- Large datasets
- Infinite sequences
- Memory efficiency
- Partial result processing

**Example:**
```kotlin
fun fibonacciSequence(): Sequence<Long> = sequence {
    var a = 0L
    var b = 1L
    while (true) {
        yield(a)
        val next = a + b
        a = b
        b = next
    }
}

// Usage:
val first10 = fibonacciSequence().take(10).toList()
```

**Real-world use:**
- Pagination
- Streaming data
- Large file processing
- Database result sets

---

### 11. **Local Functions** 🏠
Functions defined inside other functions.

**When to use:**
- Private helper logic
- Avoid code duplication within function
- Encapsulate complexity
- Access outer function variables

**Example:**
```kotlin
fun processData(data: List<Int>): String {
    fun validate(): Boolean = data.isNotEmpty()
    fun calculate(): Int = data.sum()
    
    return if (validate()) {
        "Sum: ${calculate()}"
    } else {
        "No data"
    }
}
```

**Real-world use:**
- Complex validations
- Multi-step calculations
- Private helpers
- Reduce function complexity

---

### 12. **Anonymous Functions** 🎭
Named-less functions, alternative to lambdas with explicit return.

**When to use:**
- Need explicit return type
- Multiple return points
- Non-local return behavior
- Complex function body

**Example:**
```kotlin
val divide = fun(a: Int, b: Int): Double {
    if (b == 0) return 0.0
    return a.toDouble() / b
}

// Difference from lambda:
numbers.map(fun(num): Int {
    if (num < 0) return 0  // Only exits this function
    return num * 2
})
```

**Real-world use:**
- Complex transformations
- Multiple exit points
- Explicit type requirements
- Functional programming

---

## Where to Use Them

### 📱 **Android Development**

1. **UI Event Handlers**
```kotlin
Button(onClick = { /* lambda */ })
```

2. **RecyclerView Adapters**
```kotlin
list.filter { it.isActive }.map { it.name }
```

3. **LiveData/Flow Transformations**
```kotlin
userFlow.map { it.name }.filter { it.isNotEmpty() }
```

4. **View Extensions**
```kotlin
fun View.show() { visibility = View.VISIBLE }
```

5. **Coroutines**
```kotlin
suspend fun fetchData(): Result<Data>
```

---

### 💼 **Business Logic**

1. **Data Validation**
```kotlin
fun validateEmail(email: String): Boolean
```

2. **Calculations**
```kotlin
fun calculateDiscount(price: Double, percentage: Double): Double
```

3. **Data Transformation**
```kotlin
fun List<User>.toUserDTOs() = map { it.toDTO() }
```

---

### 🔧 **Utility & Helpers**

1. **String Operations**
```kotlin
fun String.toTitleCase(): String
```

2. **Date Formatting**
```kotlin
fun Date.formatAsYYYYMMDD(): String
```

3. **Collection Helpers**
```kotlin
fun <T> List<T>.secondOrNull(): T?
```

---

## Quick Reference

| Need to... | Use... |
|------------|--------|
| Simple operation | Basic function |
| Pass function as parameter | Higher-order function |
| Process collections | Lambda with map/filter |
| Add method to existing class | Extension function |
| Create DSL-like syntax | Infix function |
| Overload operators | Operator function |
| Optimize performance | Inline function |
| Configure objects | Scope functions (apply, also) |
| Handle nullable values | Scope functions (let, run) |
| Recursive algorithm | Tail recursive |
| Process large datasets | Sequences |
| Private helper in function | Local function |
| Need explicit return type | Anonymous function |

---

## 🎓 Learning Path

1. **Start with:** Basic Functions
2. **Then learn:** Lambdas & Higher-Order Functions
3. **Practice:** Extension Functions
4. **Master:** Scope Functions
5. **Advanced:** Inline, Tail Recursive, Sequences

---

## 📝 Best Practices

✅ **Do:**
- Use meaningful function names
- Keep functions small and focused
- Use extension functions for utilities
- Leverage lambdas for collections
- Use scope functions for cleaner code

❌ **Don't:**
- Make functions too long
- Nest functions too deeply
- Overuse inline functions
- Create unnecessary lambdas
- Ignore naming conventions

---

## 🚀 Run the Examples

Open the **kotlin-programming** module in Android Studio and:
1. Run the app
2. Click "Run All Demonstrations"
3. Check Logcat for detailed output
4. Explore the code in the `functions` package

Each file demonstrates different function types with practical examples!

---

**Happy Coding! 🎉**

