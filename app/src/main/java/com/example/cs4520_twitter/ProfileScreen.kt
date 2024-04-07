package com.example.cs4520_twitter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.cs4520_twitter.data.Bab
import com.example.cs4520_twitter.data.User
import com.example.cs4520_twitter.data.UserProfile
import com.example.cs4520_twitter.ui.theme.backgroundBrushBlueYellowTheme
import com.example.cs4520_twitter.ui.theme.blue
import java.text.SimpleDateFormat

val dummyUsername = "babble_user"
val dummyUser = User("_", dummyUsername, "", "password")
val dummyBab = Bab(
    id = "0",
    authorUser = dummyUser,
    content = "I made a post!",
    date = SimpleDateFormat("yyyy-MM-dd").parse("2024-04-07"),
    likes = 3,
    likedUserList = mutableListOf<String>())
val dummyProfile = UserProfile(id = "user1",
    user = dummyUser,
    description = "Hello!",
    followerCount = 0,
    followerList = mutableListOf<User>(),
    followingCount = 0,
    followingList = mutableListOf<User>(),
    babCount = 4)

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun UserProfileScreen(profile : UserProfile = dummyProfile) {
    val configuration = LocalConfiguration.current
    val maxHeight = configuration.screenHeightDp
    val maxWidth = configuration.screenWidthDp
    val dummyFollowerCount = profile.followerCount
    val dummyFollowingCount = profile.followingCount
    val dummyBabCount = profile.babCount
    val usernameSize = 20 // hardcoded dim.'s
    val dataTextSize = 14
    val iconDim = maxWidth/3.8 // around a quarter of the screen width
    // I guess we would look up the bab database according to profile user id
    val dummyBabList = mutableListOf<Bab>(dummyBab, dummyBab, dummyBab, dummyBab)

    Box(modifier = with (Modifier) {
        fillMaxSize().background(backgroundBrushBlueYellowTheme)
    })
    {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (userBanner,
                userIcon,
                username,
                followerCount,
                followingCount,
                babCount,
                editButton,
                myFollowersButton,
                userDesc,
                babColumn// babs made by this user
            ) = createRefs()

            var userDescText by remember { mutableStateOf("Hello!") }

            Box(modifier = Modifier // this is the white banner, has the screen's width
                .fillMaxWidth()
                .background(Color.White)
                .height((0.25 * maxHeight).dp)
                .constrainAs(userBanner) {
                    top.linkTo(parent.top, margin = (maxWidth * 0.35).dp)
                })

            GlideImage( // this is the user icon image
                model = "https://m.media-amazon.com/images/I/31YObRg58fL._SY445_SX342_.jpg",
                contentScale = ContentScale.Crop,
                failure = placeholder(ColorPainter(Color.White)),
                loading = placeholder(ColorPainter(Color.White)),
                contentDescription = "",
                modifier = Modifier
                    .size(iconDim.dp) // hardcoded icon dim.'s, etc.
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
                        top.linkTo(userBanner.top, margin = (-iconDim / 2).dp)
                        // Place icon midway the screen's width
                        absoluteLeft.linkTo(
                            userBanner.absoluteLeft,
                            margin = (maxWidth / 2 - iconDim / 2).dp
                        )
                    })

            // Username text
            Text(dummyUsername,
                // textAlign = TextAlign.Center,
                fontSize = usernameSize.sp,
                modifier = Modifier
                    .constrainAs(username) {
                        top.linkTo(userIcon.bottom, margin = (5).dp)
                        absoluteLeft.linkTo(
                            userBanner.absoluteLeft,
                            margin = (maxWidth / 2 - (dummyUsername.length
                                    * (usernameSize / 2)) / 2).dp
                        )
                    })

            // Num. of Followers text
            Text(
                "Followers: $dummyFollowerCount",
                color = blue,
                fontSize = dataTextSize.sp,
                modifier = Modifier
                    .constrainAs(followerCount) {
                        top.linkTo(userBanner.top, margin = (10).dp)
                        absoluteLeft.linkTo(
                            userBanner.absoluteLeft,
                            margin = 10.dp
                        )
                    })

            // Num. of Following text
            Text(
                "Following: $dummyFollowingCount",
                color = blue,
                fontSize = dataTextSize.sp,
                modifier = Modifier
                    .constrainAs(followingCount) {
                        top.linkTo(followerCount.bottom, margin = (1).dp)
                        absoluteLeft.linkTo(
                            userBanner.absoluteLeft,
                            margin = 10.dp
                        )
                    })

            // Num. of Posts text
            Text(
                "Babs: $dummyBabCount",
                color = blue,
                fontSize = dataTextSize.sp,
                modifier = Modifier
                    .constrainAs(babCount) {
                        top.linkTo(followingCount.bottom, margin = (1).dp)
                        absoluteLeft.linkTo(
                            userBanner.absoluteLeft,
                            margin = 10.dp
                        )
                    })
            // Button for editing profile
            Button(
                onClick = {},
                modifier = Modifier
                    .height(35.dp)
                    .width(110.dp)
                    .constrainAs(editButton) {
                        top.linkTo(userDesc.bottom, margin = 10.dp)
                        absoluteRight.linkTo(
                            userBanner.absoluteRight,
                            margin = 10.dp
                        )
                    },
                contentPadding = PaddingValues(0.dp),
                border = BorderStroke(2.dp, blue),
                colors = ButtonDefaults.buttonColors(contentColor = blue, containerColor = Color.White)
            ) {
                Text(
                    text = "Edit",
                    fontSize = 14.sp
                )
            }
            // Button for viewing my followers
            Button(
                onClick = {},
                modifier = Modifier
                    .height(35.dp)
                    .width(110.dp)
                    .constrainAs(myFollowersButton) {
                        top.linkTo(userBanner.top, margin = 10.dp)
                        absoluteRight.linkTo(
                            userBanner.absoluteRight,
                            margin = 10.dp
                        )
                    },
                contentPadding = PaddingValues(0.dp),
                border = BorderStroke(2.dp, blue),
                colors = ButtonDefaults.buttonColors(contentColor = blue, containerColor = Color.White)
            ) {
                Text(
                    text = "My Followers",
                    fontSize = 14.sp
                )
            }
            // user desc.
            TextField(
                value = userDescText,
                onValueChange = { userDescText = it },
                // enabled = false, // uncomment this to disallow users from typing
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor =  Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent),
                modifier = Modifier
                    .height(70.dp)
                    .width(350.dp)
                    .constrainAs(userDesc) {
                        top.linkTo(username.bottom, margin = 0.dp)
                        absoluteRight.linkTo(
                            userBanner.absoluteRight,
                            margin = 25.dp
                        )
                    })

            // List of Babs
            LazyColumn(userScrollEnabled = true,
                modifier = Modifier
                    .height(400.dp)
                    .width((maxWidth * 0.93).dp)
                    .constrainAs(babColumn) {
                        top.linkTo(userBanner.bottom, margin = 10.dp)
                        absoluteLeft.linkTo(parent.absoluteLeft, margin = ((maxWidth * 0.07)/2).dp)
                    }) {
                items(
                    count = dummyBabList.size,
                    itemContent = { index ->
                        BabCard(dummyBabList[index])
                    }
                )
            }
        }
    }
}

// Bab card for Bab list
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BabCard(bab : Bab) {
    val configuration = LocalConfiguration.current
    val maxHeight = configuration.screenHeightDp
    val maxWidth = configuration.screenWidthDp
    Card(modifier = Modifier
        .fillMaxWidth().height((maxHeight/5.25).dp)
        .padding(horizontal = 8.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(corner = CornerSize(15.dp))
    ) {
        Row {
            ConstraintLayout (modifier = Modifier.fillMaxWidth().height((maxHeight/5).dp)) {
                val (date, username, content, likes, userIcon, heart) = createRefs()
                GlideImage( // this is the icon image
                    model = "https://m.media-amazon.com/images/I/31YObRg58fL._SY445_SX342_.jpg",
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
                        .constrainAs(userIcon) {
                            // halfway icon height
                            top.linkTo(parent.top, margin = (maxHeight/10 - 60).dp)
                            // Place icon midway the screen's width
                            absoluteLeft.linkTo(
                                parent.absoluteLeft,
                                margin = (10).dp
                            )
                        })

                Text("@" + bab.authorUser.username,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.constrainAs(username) {
                        bottom.linkTo(userIcon.top, margin = (-15).dp)
                        absoluteLeft.linkTo(userIcon.absoluteRight, margin = 5.dp)
                    })

                Text(bab.content, modifier = Modifier.constrainAs(content) {
                    top.linkTo(username.bottom)
                    absoluteLeft.linkTo(userIcon.absoluteRight, margin = 5.dp)
                })

                Text("Likes: " + bab.likes.toString(), color = blue,
                    modifier = Modifier.constrainAs(likes) {
                        top.linkTo(parent.bottom, margin = (-20).dp)
                        absoluteLeft.linkTo(parent.absoluteLeft, margin = 20.dp)
                    })

                // Date object may be deprecated
                Text("Date: " + bab.date.toString(), color = blue,
                    modifier = Modifier.constrainAs(date) {
                        top.linkTo(parent.bottom, margin = (-20).dp)
                        absoluteLeft.linkTo(likes.absoluteRight, margin = 10.dp)
                    })
            }
        }
    }
}

// --------- Would be nice to have:
@Composable
fun AbstractButton() {
    // TODO: To take in a theme and function
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun UserIcon() {
    GlideImage(
        model = "https://m.media-amazon.com/images/I/31YObRg58fL._SY445_SX342_.jpg",
        contentDescription = "",
        modifier = Modifier
            .size(150.dp)
            .clip(RoundedCornerShape(10))
    )
}