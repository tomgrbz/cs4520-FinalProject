package com.example.cs4520_twitter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    private val blue :  Color = Color(0xFF9BAAF8) // 0xFF9BAAF8
    private val transition :  Color = Color(0xFFB9C1F1)
    private val yellow :  Color = Color(0xFFFFF5E2) // 0xFFFFF5E2
    private val backgroundBrushBlueYellowTheme : Brush = Brush.linearGradient(
        colors = listOf(blue, transition, yellow),
        start = Offset(0f, 0f),
        Offset(0f, Float.POSITIVE_INFINITY),
        tileMode = TileMode.Clamp)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(color = Color.White) {}
                    AppNavHost(navController = rememberNavController())
            }
        }
    }
}

