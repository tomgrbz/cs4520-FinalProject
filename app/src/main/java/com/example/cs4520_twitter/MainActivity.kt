package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

class MainActivity : ComponentActivity() {
    private val blue :  androidx.compose.ui.graphics.Color = Color(0xFF9BAAF8) // 0xFF9BAAF8
    private val transition :  androidx.compose.ui.graphics.Color = Color(0xFFB9C1F1)
    private val yellow :  androidx.compose.ui.graphics.Color = Color(0xFFFFF5E2) // 0xFFFFF5E2
    private val backgroundBrushBlueYellowTheme : Brush = Brush.linearGradient(
        colors = listOf(blue, transition, yellow),
        start = Offset(0f, 0f),
        Offset(0f, Float.POSITIVE_INFINITY),
        tileMode = TileMode.Clamp)

    @Composable
    @Preview(showBackground = true)
    fun UserProfile() {
        Box(modifier = with (Modifier) {
            fillMaxSize().background(backgroundBrushBlueYellowTheme)
        })
        {
            //Stack stuff on here
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (userBanner, userIcon, ) = createRefs()
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .height(175.dp)
                    .constrainAs(userBanner) {
                    top.linkTo(parent.top, margin = 140.dp)
                }) {
                    Text(text ="User banner", color = Color.Black)
                }
            }
            }
        }

    @Composable
    fun UserBanner() {
        // Should take in inputs such as profile pic, user data
        Box(modifier = with (Modifier) {
            fillMaxWidth().background(Color.White).height(200.dp)
        }) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        }
    }
}

