package com.venkat.kotlin_programming

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.venkat.kotlin_programming.functions.*
import com.venkat.kotlin_programming.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Run all function demonstrations in logcat
        runFunctionDemonstrations()
        
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    KotlinProgrammingScreen(
                        modifier = Modifier.padding(innerPadding),
                        onRunDemos = { runFunctionDemonstrations() }
                    )
                }
            }
        }
    }
    
    private fun runFunctionDemonstrations() {
        Log.d("KotlinFunctions", "========================================")
        Log.d("KotlinFunctions", "KOTLIN FUNCTIONS DEMONSTRATION")
        Log.d("KotlinFunctions", "========================================")
        
        demonstrateBasicFunctions()
        demonstrateHigherOrderFunctions()
        demonstrateLambdaFunctions()
        demonstrateExtensionFunctions()
        demonstrateInfixAndOperatorFunctions()
        demonstrateInlineAndScopeFunctions()
        demonstrateTailRecursiveAndGeneratorFunctions()
        demonstrateLocalAndAnonymousFunctions()
        
        Log.d("KotlinFunctions", "\n========================================")
        Log.d("KotlinFunctions", "ALL DEMONSTRATIONS COMPLETED!")
        Log.d("KotlinFunctions", "Check Logcat for detailed output")
        Log.d("KotlinFunctions", "========================================")
    }
}

@Composable
fun KotlinProgrammingScreen(
    modifier: Modifier = Modifier,
    onRunDemos: () -> Unit = {}
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Kotlin Functions",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Complete Guide & Examples",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Function types list
            val functionTypes = listOf(
                "1. Basic Functions" to "Simple, parameters, return types, default args",
                "2. Higher-Order Functions" to "Functions as parameters and return values",
                "3. Lambda Functions" to "Anonymous functions with concise syntax",
                "4. Extension Functions" to "Add functions to existing classes",
                "5. Infix Functions" to "Call functions without dots and parentheses",
                "6. Operator Functions" to "Overload operators (+, -, *, /)",
                "7. Inline Functions" to "Performance optimization for lambdas",
                "8. Scope Functions" to "let, run, with, apply, also",
                "9. Tail Recursive" to "Optimized recursive functions",
                "10. Generator Functions" to "Lazy sequences for efficient processing",
                "11. Local Functions" to "Functions inside functions",
                "12. Anonymous Functions" to "Alternative to lambdas with explicit returns"
            )
            
            functionTypes.forEach { (title, description) ->
                FunctionTypeCard(title = title, description = description)
                Spacer(modifier = Modifier.height(12.dp))
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Button(
                onClick = onRunDemos,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Run All Demonstrations",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "📱 Check Logcat for detailed output",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "💡 Explore the code in the 'functions' package",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun FunctionTypeCard(title: String, description: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KotlinProgrammingScreenPreview() {
    MyApplicationTheme {
        KotlinProgrammingScreen()
    }
}


