package com.venkat.run_time_permissions

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.request).setOnClickListener {
            checkForPermissions()
        }

        val composeView = findViewById<ComposeView>(R.id.compose_view)
        composeView.setContent {
            MyComposableContent()
        }
    }


    @Composable
    fun MyComposableContent() {
        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()){
            isGranted ->
            if (isGranted) {
                Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Permission Denied", Toast.LENGTH_SHORT).show()
            }

        }
        Button(onClick = {
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                         }, modifier = Modifier.padding(20.dp)) {
            Text("Request Permission")
        }
    }

    private fun checkForPermissionsCompose() {

    }

    private fun checkForPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_GRANTED ){
            Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT).show()
        }else{
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA), 101)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 101) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }

    }
}

