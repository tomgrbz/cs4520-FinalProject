package com.example.cs4520_twitter.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.cs4520_twitter.data_layer.database.UserProfileEntity
import com.example.cs4520_twitter.data_layer.database.dummyImageURL
import com.example.cs4520_twitter.vms.FollowingScreenViewModel
import com.example.cs4520_twitter.ui.theme.blue

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun FollowingScreen(profile: UserProfileEntity) {
    val viewModel: FollowingScreenViewModel = viewModel() // TODO factory
    val configuration = LocalConfiguration.current
    val maxHeight = configuration.screenHeightDp

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
                        Text(profile.user.username,
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("profile_header"),
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
            count = profile.followingCount,
            itemContent = { index ->
                val followedUser = profile.followingList[index]
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            PaddingValues(10.dp, 10.dp, 10.dp, 0.dp)
                        )
                        .testTag("following_card"),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(corner = CornerSize(15.dp)),
                ) {
                    ConstraintLayout(
                        modifier = Modifier.fillMaxWidth().padding((maxHeight * 0.01).dp),
                    ) {
                        val (image, name, btn) = createRefs()
                        GlideImage( // this is the icon image
                            model = dummyImageURL,
                            contentScale = ContentScale.Crop,
                            loading = placeholder(ColorPainter(Color.White)),
                            failure = placeholder(ColorPainter(Color.White)),
                            contentDescription = "",
                            modifier = Modifier
                                .size(60.dp) // hardcoded icon dim.'s, etc.
                                .shadow(
                                    elevation = 5.dp,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .border(
                                    width = 1.dp,
                                    color = blue,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .constrainAs(image) {
                                    start.linkTo(parent.absoluteLeft)
                                }
                        )
                        Text(text = followedUser.username,
                            modifier = Modifier
                                .constrainAs(name) {
                                start.linkTo(image.end, margin = 10.dp)
                            })
                        Button(
                            onClick = { viewModel.unfollow(followedUser.userID, profile.user.userID) },
                            contentPadding = PaddingValues(0.dp),
                            border = BorderStroke(2.dp, blue
                            ),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = blue,
                                containerColor = Color.White),
                            modifier = Modifier
                                .height(35.dp)
                                .width(110.dp)
                                .constrainAs(btn) {
                                    top.linkTo(parent.top, 10.dp)
                                    end.linkTo(parent.end)
                                }
                        ) {
                            Text(text = "Unfollow", fontSize = 14.sp)
                        }
                    }
                }
            })
        }
    }
}