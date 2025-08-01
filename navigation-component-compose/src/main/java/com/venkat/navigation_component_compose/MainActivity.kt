package com.venkat.navigation_component_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.venkat.navigation_component_compose.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {  padding ->
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home",
                        modifier =
                        Modifier.padding()
                    ) {
                        composable("home") {
                            HomeScreen(navController = navController)
                        }
                        composable("details") {
                            DetailsScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun HomeScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center){
        Text(text = "Home Screen", modifier = Modifier.clickable {
            navController.navigate("details")
        })
    }

}

@Composable
fun DetailsScreen(navController: NavController) {
    Text(text = "Details Screen", modifier = Modifier.clickable {
        navController.navigate("home")
    })
}
