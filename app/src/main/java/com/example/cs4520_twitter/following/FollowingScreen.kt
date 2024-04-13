package com.example.cs4520_twitter.following

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun FollowingScreen() {
    val viewModel: FollowingScreenViewModel = viewModel()
    val configuration = LocalConfiguration.current
    val maxHeight = configuration.screenHeightDp
    val maxWidth = configuration.screenWidthDp

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
                        .height(80.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(corner = CornerSize(15.dp)),
                ) {
                    Row(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
                        Text(text = followedUser.username)
                        Spacer(modifier = Modifier.weight(1f))
                        Button(
                            onClick = { viewModel.unfollow(followedUser.id) },
                            contentPadding = PaddingValues(0.dp),
                            border = BorderStroke(2.dp, Color.Blue
                            ), // todo swap with theme blue on merge
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.Blue,
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