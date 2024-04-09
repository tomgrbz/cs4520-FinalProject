package com.example.cs4520_twitter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.cs4520_twitter.data.Bab
import com.example.cs4520_twitter.data.User
import java.text.SimpleDateFormat

private val darkerBlue :  androidx.compose.ui.graphics.Color = Color(0xFF6880FF) // 0xFF9BAAF8
val darkerPink = Color(0xFFFF7BF1)
private val blue :  androidx.compose.ui.graphics.Color = Color(0xFF9BAAF8) // 0xFF9BAAF8
private val transition :  androidx.compose.ui.graphics.Color = Color(0xFFB9C1F1)
private val yellow :  androidx.compose.ui.graphics.Color = Color(0xFFFFF5E2) // 0xFFFFF5E2
private val backgroundBrushBlueYellowTheme : Brush = Brush.linearGradient(
    colors = listOf(blue, transition, yellow),
    start = Offset(0f, 0f),
    Offset(0f, Float.POSITIVE_INFINITY),
    tileMode = TileMode.Clamp)

val dummyUsername = "babble_user"
val dummyUser = User("_", dummyUsername, "", "password")
val dummyBab = Bab(
    id = "0",
    authorUser = dummyUser,
    content = "I made a post!",
    date = SimpleDateFormat("yyyy-MM-dd").parse("2024-04-07"),
    likes = 3,
    likedUserList = mutableListOf<String>())
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AddBabScreenComposable() {
    val configuration = LocalConfiguration.current
    val maxHeight = configuration.screenHeightDp
    val maxWidth = configuration.screenWidthDp

    Box(modifier = with (Modifier) {
        fillMaxSize().background(backgroundBrushBlueYellowTheme)
    })
    {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
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
            bottomBar = { },
            floatingActionButton = {
            }
        ) { innerPadding ->
            ConstraintLayout(
                modifier = Modifier
                    .padding(innerPadding)
            ) { // References
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
                            top.linkTo(userCard.bottom, margin = (maxHeight*0.03).dp)
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

// Bab card for Bab list
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AddBabUserCard(bab : Bab) {
    val configuration = LocalConfiguration.current
    val maxHeight = configuration.screenHeightDp
    val maxWidth = configuration.screenWidthDp
    val iconSize = 60
    Card(modifier = Modifier
        .width((maxWidth * 0.9).dp)
        .height((maxHeight * 0.13).dp)
        .padding(horizontal = 8.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(corner = CornerSize(15.dp))
    ) {
        Row {
            ConstraintLayout (modifier = Modifier
                .fillMaxWidth()
                .height((maxHeight / 5).dp)) {
                val (date, username, userIcon) = createRefs()
                GlideImage( // this is the icon image
                    model = "https://m.media-amazon.com/images/I/31YObRg58fL._SY445_SX342_.jpg",
                    contentScale = ContentScale.Crop,
                    loading = placeholder(ColorPainter(Color.White)),
                    failure = placeholder(ColorPainter(Color.White)),
                    contentDescription = "",
                    modifier = Modifier
                        .size(iconSize.dp) // hardcoded icon dim.'s, etc.
                        .shadow(
                            elevation = 5.dp,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = blue,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .constrainAs(userIcon) {
                            // halfway icon height
                            top.linkTo(parent.top, margin = ((maxHeight * 0.115) / 2 - (iconSize /2)).dp)
                            // Place icon midway the screen's width
                            absoluteLeft.linkTo(
                                parent.absoluteLeft,
                                margin = (10).dp
                            )
                        })

                Text("@" + bab.authorUser.username,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.constrainAs(username) {
                        bottom.linkTo(userIcon.top, margin = (-40).dp)
                        absoluteLeft.linkTo(userIcon.absoluteRight, margin = 5.dp)
                    })

            }
        }
    }
}

