package com.venkat.view_model_demo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold { innerPadding ->
                UsingBox(innerPadding)
            }

        }
    }
}

@Composable
fun UsingCompose(innerPadding: PaddingValues) {

    var counter by remember { mutableStateOf(0) }

    Column {
        Text(
            text = counter.toString(),
            fontSize = 200.sp,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .wrapContentHeight(Alignment.CenterVertically),
            textAlign = TextAlign.Center
        )
        Button(
            onClick = {counter++},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Counter")
        }
    }
}

@Composable
fun UsingBox(innerPadding: PaddingValues){

    val viewModel: CounterViewModel = viewModel()

    val count by viewModel.count.observeAsState(0)


    var counter by remember { mutableStateOf(2) }
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = count.toString(),
            fontSize = 200.sp,
            modifier = Modifier.align(Alignment.Center)
        )
        Button(
            onClick = {counter++},
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter).padding(100.dp)
        ) {
            Text(text = "Toast")
        }
        Button(
            onClick = {
                viewModel.increment()
            },
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth()
        ) {
            Text(text = "Counter")
        }

    }


}