package com.example.cs4520_twitter.composables

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.cs4520_twitter.ui.theme.backgroundBrushBlueYellowTheme
import com.example.cs4520_twitter.ui.theme.blue
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.cs4520_twitter.data_layer.database.UserProfileEntity
import com.example.cs4520_twitter.data_layer.database.dummyBabList
import com.example.cs4520_twitter.data_layer.database.dummyImageURL
import com.example.cs4520_twitter.data_layer.database.dummyProfile
import com.example.cs4520_twitter.data_layer.database.dummyUser
import com.example.cs4520_twitter.data_layer.database.dummyUsername
import com.example.cs4520_twitter.nav.NavigationItem
import com.example.cs4520_twitter.vms.ProfileViewModel

// File contains screen composable. Dummy data is imported from the database folder.
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun UserProfileScreen(profile : UserProfileEntity = dummyProfile, navController: NavController) {
    val configuration = LocalConfiguration.current
    val maxHeight = configuration.screenHeightDp
    val maxWidth = configuration.screenWidthDp
    val usernameSize = 20 // hardcoded dim.'s
    val dataTextSize = 14
    val iconDim = maxWidth * 0.30 // around a third of the screen width

    val viewModel: ProfileViewModel = viewModel(factory = ProfileViewModel.Factory)
    viewModel.fetchLoggedInUserProfile() // fetch user profile
    viewModel.fetchLoggedInUserBabs()    // fetch babs

    val babs by viewModel.babList.collectAsState()                // babs of this user profile
    val userProfile by viewModel.loggedInProfile.collectAsState() // user profile

    Box(modifier = with (Modifier) {
        fillMaxSize().background(backgroundBrushBlueYellowTheme)
    })
    {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (userBanner, // the white banner
                userIcon,    // user's profile image
                username,
                followerCount,
                followingCount,
                babCount,
                editButton,
                myFollowersButton, // button to navigate to followers screen
                userDesc,
                babColumn          // list of THIS user's babs
            ) = createRefs()

            Box(modifier = Modifier // this is the white banner, has the screen's width
                .fillMaxWidth()
                .background(Color.White)
                .height((0.25 * maxHeight).dp)
                .constrainAs(userBanner) {
                    top.linkTo(parent.top, margin = (maxWidth * 0.35).dp)
                })

            GlideImage( // this is the user icon image
                model = dummyImageURL,
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
            Log.i("ProfileScreen", "profile user is: " + userProfile.user.toString())
            Text(
                userProfile.user.username,
                fontSize = usernameSize.sp,
                modifier = Modifier
                    .constrainAs(username) {
                        top.linkTo(userIcon.bottom, margin = (5).dp)
                        absoluteLeft.linkTo(
                            userBanner.absoluteLeft,
                            margin = (maxWidth / 2 - (userProfile.user.username.length
                                    * (usernameSize / 2)) / 2).dp
                        )
                    })

            // Num. of Followers text
            val profileFollowerCount = userProfile.followerCount
            Text(
                "Followers: $profileFollowerCount",
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
            val profileFollowingCount = userProfile.followingCount
            Text(
                "Following: $profileFollowingCount",
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
            val profileBabCount = userProfile.babCount
            Text(
                "Babs: $profileBabCount",
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
                onClick = {
                          navController.navigate(NavigationItem.EditProfile.route)
                },
                modifier = Modifier
                    .height(35.dp)
                    .width(110.dp)
                    .constrainAs(editButton) {
                        top.linkTo(userDesc.bottom)
                        absoluteRight.linkTo(
                            userBanner.absoluteRight,
                            margin = 10.dp
                        )
                    },
                contentPadding = PaddingValues(0.dp),
                border = BorderStroke(2.dp, blue),
                colors = ButtonDefaults.buttonColors(contentColor = blue,
                    containerColor = Color.White)
            ) {
                Text(
                    text = "Edit",
                    fontSize = 14.sp
                )
            }
            // Button for viewing my followers
            Button(
                onClick = {
                          navController.navigate(NavigationItem.Followers.route)
                },
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
                colors = ButtonDefaults.buttonColors(contentColor = blue,
                    containerColor = Color.White)
            ) {
                Text(
                    text = "My Followers",
                    fontSize = 14.sp
                )
            }
            // user desc.
            Text(
                text = userProfile.description,
                modifier = Modifier
                    .height(60.dp)
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
                    .height((maxHeight * 0.45).dp) // half the screen
                    .width((maxWidth * 0.95).dp)  // most of the screen width
                    .constrainAs(babColumn) {
                        top.linkTo(userBanner.bottom, margin = 10.dp)
                        absoluteLeft.linkTo(
                            parent.absoluteLeft,
                            margin = ((maxWidth * 0.05) / 2).dp
                        )
                    }) {
                items(
                    count = babs.size,
                    itemContent = { index ->
                        BabCard(babs[index])
                    }
                )
            }
        }
    }
}
