package com.example.cs4520_twitter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

private val darkerBlue :  androidx.compose.ui.graphics.Color = Color(0xFF6880FF) // 0xFF9BAAF8
private val blue :  androidx.compose.ui.graphics.Color = Color(0xFF9BAAF8) // 0xFF9BAAF8
private val transition :  androidx.compose.ui.graphics.Color = Color(0xFFB9C1F1)
private val yellow :  androidx.compose.ui.graphics.Color = Color(0xFFFFF5E2) // 0xFFFFF5E2
private val backgroundBrushBlueYellowTheme : Brush = Brush.linearGradient(
    colors = listOf(blue, transition, yellow),
    start = Offset(0f, 0f),
    Offset(0f, Float.POSITIVE_INFINITY),
    tileMode = TileMode.Clamp)
@Preview(showBackground = true)
@Composable
fun LoginScreenComposable() {
    val configuration = LocalConfiguration.current
    val maxHeight = configuration.screenHeightDp
    val maxWidth = configuration.screenWidthDp

    Box(modifier = with (Modifier) {
        fillMaxSize().background(backgroundBrushBlueYellowTheme)
    })
    {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (babbleTitle,
                username,
                password,
                loginButton,
                registerText) = createRefs()

            var usernameText by remember { mutableStateOf("") }
            var passwordText by remember { mutableStateOf("") }

            // Username text
            Text("Babble",
                fontSize = 30.sp,
                style = TextStyle(shadow = Shadow(blurRadius = 3f)),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .constrainAs(babbleTitle) {
                        top.linkTo(parent.top, margin = (maxHeight/15).dp)
                        absoluteLeft.linkTo(
                            parent.absoluteLeft,
                            margin = (maxWidth/2.6).dp
                        )
                    })

            // user desc.
            TextField(
                value = usernameText,
                onValueChange = { usernameText = it },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    Color.Black,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor =  Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent),
                shape = RoundedCornerShape(15.dp),
                placeholder = {
                    Text("Please enter username", color = blue)
                },
                modifier = Modifier
                    .height(50.dp)
                    .width(250.dp)
                    .constrainAs(username) {
                        top.linkTo(babbleTitle.bottom, margin = (maxHeight/10).dp)
                        absoluteLeft.linkTo(
                            parent.absoluteLeft,
                            margin = (maxWidth/2 - 250/2).dp)
                    })

            // user desc.
            TextField(
                value = passwordText,
                onValueChange = { passwordText = it },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    Color.Black,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor =  Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent),
                shape = RoundedCornerShape(15.dp),
                placeholder = {
                    Text("Please enter password", color = blue)
                },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .height(50.dp)
                    .width(250.dp)
                    .constrainAs(password) {
                        top.linkTo(username.bottom, margin = (maxHeight/20).dp)
                        absoluteLeft.linkTo(
                            parent.absoluteLeft,
                            margin = (maxWidth/2 - 250/2).dp)
                    })

            // Button for viewing my followers
            Button(
                onClick = {},
                modifier = Modifier
                    .height(35.dp)
                    .width(110.dp)
                    .constrainAs(loginButton) {
                        top.linkTo(password.bottom, margin = (maxHeight/20).dp)
                        absoluteLeft.linkTo(
                            parent.absoluteLeft,
                            margin = (maxWidth/2 - 110/2).dp)
                    },
                contentPadding = PaddingValues(0.dp),
                border = BorderStroke(2.dp, blue),
                colors = ButtonDefaults.buttonColors(contentColor = blue, containerColor = Color.White)
            ) {
                Text(
                    text = "Login",
                    fontSize = 14.sp
                )
            }

            Text(
                text = "Don't have an account?\nClick to sign up!",
                color = darkerBlue,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .height(60.dp)
                    .width(350.dp)
                    .constrainAs(registerText) {
                        top.linkTo(loginButton.bottom, margin = (maxHeight/9).dp)
                        absoluteLeft.linkTo(
                            parent.absoluteLeft,
                            margin = 25.dp)
                    })
        }
    }
}
