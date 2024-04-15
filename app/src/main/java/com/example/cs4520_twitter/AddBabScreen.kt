package com.example.cs4520_twitter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.cs4520_twitter.composables.AddBabUserCard
import com.example.cs4520_twitter.data_layer.database.dummyBab
import com.example.cs4520_twitter.ui.theme.backgroundBrushBlueYellowTheme
import com.example.cs4520_twitter.ui.theme.blue

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AddBabScreen() {
    val configuration = LocalConfiguration.current
    val maxHeight = configuration.screenHeightDp
    val maxWidth = configuration.screenWidthDp

    Box(modifier = with (Modifier) {
        fillMaxSize().background(backgroundBrushBlueYellowTheme)
    })
    {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = { // Add a Bab screen title
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = Color.White,
                        titleContentColor = Color.Black,
                    ),
                    title = {
                        Text("What's the Bab?",
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center)
                    }
                )
            },
            bottomBar = {},
            floatingActionButton = {}
        ) { innerPadding ->
            ConstraintLayout(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                val (userCard, // Display the logged in user card
                    textField, // text field for writing new Bab
                    addBabButton) = createRefs()
                Box(modifier = Modifier.constrainAs(userCard) {
                    top.linkTo(parent.top, margin = (maxWidth * 0.05).dp)
                    absoluteLeft.linkTo(
                        parent.absoluteLeft,
                        margin = (maxWidth/2 - (maxWidth * 0.9)/2).dp)
                }) {
                    AddBabUserCard(dummyBab)
                }

                // Enter a new Bab text field
                var babText by remember { mutableStateOf("") }
                TextField(
                    value = babText,
                    onValueChange = { babText = it },
                    singleLine = false,
                    colors = TextFieldDefaults.colors(
                        Color.Black,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor =  Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent),
                    shape = RoundedCornerShape(15.dp),
                    placeholder = {
                        Text("What do you wanna bab about?", color = Color.Black)
                    },
                    modifier = Modifier
                        .height((maxHeight * 0.30).dp)
                        .width((maxWidth * 0.85).dp)
                        .constrainAs(textField) {
                            top.linkTo(userCard.bottom, margin = (maxHeight * 0.03).dp)
                            absoluteLeft.linkTo(
                                parent.absoluteLeft,
                                margin = (maxWidth/2 - (maxWidth * 0.85)/2).dp)
                        })

                // Button to add a bab
                Button(
                    onClick = {},
                    modifier = Modifier
                        .height(40.dp)
                        .width(110.dp)
                        .constrainAs(addBabButton) {
                            top.linkTo(textField.bottom, margin = (maxHeight/20).dp)
                            absoluteLeft.linkTo(
                                parent.absoluteLeft,
                                margin = (maxWidth/2 - 110/2).dp)
                        },
                    contentPadding = PaddingValues(0.dp),
                    border = BorderStroke(2.dp, blue),
                    colors = ButtonDefaults.buttonColors(contentColor = blue, containerColor = Color.White)
                ) {
                    Text(
                        text = "Post",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}