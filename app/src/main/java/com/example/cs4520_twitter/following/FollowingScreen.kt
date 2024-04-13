package com.example.cs4520_twitter.following

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun FollowingScreen() {
    val viewModel: FollowingScreenViewModel = viewModel()
    val configuration = LocalConfiguration.current
    val maxHeight = configuration.screenHeightDp

    val blue :  Color = Color(0xFF9BAAF8)
    Scaffold(
        containerColor = Color.Transparent,
        topBar = { // Contain the feed title
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                ),
                title = {
                    Column() {
                        Text("get-username",
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center)
                        Text(text = "Following",
                            Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp)
                    }
                }
            )
        },
        bottomBar = {}, // navigation bar already on main
        floatingActionButton = {} // we won't used this for the feed screen
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(0.dp),
        ) {items(
            count = viewModel.following.size,
            itemContent = { index ->
                val followedUser = viewModel.following[index]
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            PaddingValues(10.dp, 10.dp, 10.dp, 0.dp)
                        )
                        .height((maxHeight * 0.1).dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(corner = CornerSize(15.dp)),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
//                        GlideImage( // this is the icon image
//                            model = dummyImageURL,
//                            contentScale = ContentScale.Crop,
//                            loading = placeholder(ColorPainter(Color.White)),
//                            failure = placeholder(ColorPainter(Color.White)),
//                            contentDescription = "",
//                            modifier = Modifier
//                                .size(60.dp) // hardcoded icon dim.'s, etc.
//                                .shadow(
//                                    elevation = 5.dp,
//                                    shape = RoundedCornerShape(10.dp)
//                                )
//                                .border(
//                                    width = 1.dp,
//                                    color = blue,
//                                    shape = RoundedCornerShape(10.dp)
//                                )
//                                .constrainAs(userIcon) { todo("remove")
//                                    // halfway icon height
//                                    top.linkTo(parent.top, margin = (maxHeight * 0.1 - 65).dp)
//                                    // Place icon midway the screen's width
//                                    absoluteLeft.linkTo(
//                                        parent.absoluteLeft,
//                                        margin = (10).dp
//                                    )
//                                })

                        Text(text = followedUser.username)
                        Spacer(modifier = Modifier.weight(1f))
                        Button(
                            onClick = { viewModel.unfollow(followedUser.id) },
                            contentPadding = PaddingValues(0.dp),
                            border = BorderStroke(2.dp, blue
                            ),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = blue,
                                containerColor = Color.White),
                            modifier = Modifier
                                .height(35.dp)
                                .width(110.dp)
                        ) {
                            Text(text = "Unfollow", fontSize = 14.sp)
                        }
                    }
                }
            })
        }
    }
}