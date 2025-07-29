package com.venkat.view_model_demo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.venkat.view_model_demo.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    private lateinit var counter : TextView

    private lateinit var viewModel: CounterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        counter = findViewById<TextView>(R.id.countText)

        viewModel = ViewModelProvider(this)[CounterViewModel::class.java]

        counter.text = viewModel.count.toString()

        findViewById<Button>(R.id.counter).setOnClickListener {
            viewModel.increment()
        }

        viewModel.count.observe(this){
            counter.text = it.toString()
        }

        findViewById<Button>(R.id.next).setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}