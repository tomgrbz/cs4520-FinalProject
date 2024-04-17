package com.example.cs4520_twitter.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cs4520_twitter.ui.theme.backgroundBrushBlueYellowTheme
import com.example.cs4520_twitter.ui.theme.blue
import com.example.cs4520_twitter.ui.theme.darkerBlue
import com.example.cs4520_twitter.vms.LoginViewModel

@Preview(showBackground = true)
@Composable
fun LoginScreen() {
    val configuration = LocalConfiguration.current // for obtaining screen dimensions
    val maxHeight = configuration.screenHeightDp
    val maxWidth = configuration.screenWidthDp
    val viewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)

    val registerMessage = "Don't have an account?\nClick to sign up!"
    val loginMessage = "Already have an account?\nClick to log in!"

    // Use to show a loading animation while making api calls
    val isLoading by viewModel.isLoading.collectAsState()

    Box(modifier = with(Modifier) {
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
            // determine if user is logging in or registering
            var inLoginMode by remember { mutableStateOf(true) }
            var registerOrLoginText by remember { mutableStateOf(registerMessage) }

            // Babble title text
            val titleFontSize = 30
            Text("Babble",
                fontSize = titleFontSize.sp,
                style = TextStyle(shadow = Shadow(blurRadius = 3f)),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .constrainAs(babbleTitle) {
                        top.linkTo(parent.top, margin = (maxHeight * 0.1).dp)
                        absoluteLeft.linkTo(
                            parent.absoluteLeft,
                            margin = ((maxWidth - titleFontSize * 3) / 2).dp // center text
                        )
                    })

            // username text field
            TextField(
                value = usernameText,
                onValueChange = { usernameText = it },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    Color.Black,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(15.dp),
                placeholder = {
                    Text("Please enter username", color = blue)
                },
                modifier = Modifier
                    .height(50.dp)
                    .width(250.dp)
                    .constrainAs(username) {
                        top.linkTo(babbleTitle.bottom, margin = (maxHeight * 0.1).dp)
                        absoluteLeft.linkTo(
                            parent.absoluteLeft,
                            margin = (maxWidth / 2 - 250 / 2).dp
                        )
                    })

            // password text field
            TextField(
                value = passwordText,
                onValueChange = { passwordText = it },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    Color.Black,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(15.dp),
                placeholder = {
                    Text("Please enter password", color = blue)
                },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .height(50.dp)
                    .width(250.dp)
                    .constrainAs(password) {
                        top.linkTo(username.bottom, margin = (maxHeight * 0.05).dp)
                        absoluteLeft.linkTo(
                            parent.absoluteLeft,
                            margin = (maxWidth / 2 - 250 / 2).dp
                        )
                    })

            // Button for login
            Button(
                onClick = {
                    if (inLoginMode) {
                        viewModel.login(usernameText, passwordText)
                    } else {
                        // otherwise, sign up. VM makes check if fields are blank or not
                        viewModel.signUp(usernameText, passwordText)
                    }
                },
                modifier = Modifier
                    .height(35.dp)
                    .width(110.dp)
                    .constrainAs(loginButton) {
                        top.linkTo(password.bottom, margin = (maxHeight * 0.1).dp)
                        absoluteLeft.linkTo(
                            parent.absoluteLeft,
                            margin = (maxWidth / 2 - 110 / 2).dp
                        )
                    },
                contentPadding = PaddingValues(0.dp),
                border = BorderStroke(2.dp, blue),
                colors = ButtonDefaults.buttonColors(
                    contentColor = blue,
                    containerColor = Color.White
                )
            ) {
                if (inLoginMode) {
                    Text(
                        text = "Login", // display login if in login mode
                        fontSize = 14.sp
                    )
                } else {
                    Text(
                        text = "Register", // else display register
                        fontSize = 14.sp
                    )
                }
            }

            Text(text = registerOrLoginText,
                color = darkerBlue,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .height(60.dp)
                    .width(350.dp)
                    .clickable(onClick = { // press on text to register or log in
                        inLoginMode = !inLoginMode
                        registerOrLoginText =
                            if (registerOrLoginText == loginMessage) {
                                registerMessage
                            } else {
                                loginMessage
                            }
                    })
                    .constrainAs(registerText) {
                        top.linkTo(loginButton.bottom, margin = (maxHeight * 0.1).dp)
                        absoluteLeft.linkTo(
                            parent.absoluteLeft,
                            margin = 25.dp
                        )
                    })
        }
    }
}
