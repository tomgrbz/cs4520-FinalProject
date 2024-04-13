package com.example.cs4520_twitter

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.cs4520_twitter.nav.AppNavHost
import com.example.cs4520_twitter.nav.NavBar
import com.example.cs4520_twitter.ui.theme.backgroundBrushBlueYellowTheme

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                Scaffold(bottomBar = {
                    NavBar(navController)
                }) {
                    Box(modifier = with (Modifier) {
                        fillMaxSize().background(backgroundBrushBlueYellowTheme)
                    }) // TODO ("delete after?")
                    LoginScreenComposable()
                    AppNavHost(navController = navController)
                }
            }
        }
    }
}