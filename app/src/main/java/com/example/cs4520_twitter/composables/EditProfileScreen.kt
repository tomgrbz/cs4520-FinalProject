package com.example.cs4520_twitter.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.cs4520_twitter.ui.theme.backgroundBrushBlueYellowTheme
import com.example.cs4520_twitter.ui.theme.blue
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.cs4520_twitter.R
import com.example.cs4520_twitter.data_layer.database.UserProfileEntity
import com.example.cs4520_twitter.data_layer.database.dummyBab
import com.example.cs4520_twitter.data_layer.database.dummyImageURL
import com.example.cs4520_twitter.data_layer.database.dummyProfile
import com.example.cs4520_twitter.data_layer.database.dummyUsername
import com.example.cs4520_twitter.nav.NavigationItem
import com.example.cs4520_twitter.vms.EditProfileViewModel
import com.example.cs4520_twitter.vms.ProfileViewModel

// File contains screen composable. Dummy data is imported from the database folder.
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun EditProfileScreen(navController: NavController) {
    val viewModel: EditProfileViewModel = viewModel(factory = EditProfileViewModel.Factory)
    viewModel.fetchLoggedInUserProfile() // fetch user profile
    viewModel.fetchLoggedInUserBabs()    // fetch babs

    val babs by viewModel.babList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    // babs of this user profile
    // babs of this user profile
    val userProfile by viewModel.loggedInProfile.collectAsState() // user profile

    val configuration = LocalConfiguration.current
    val maxHeight = configuration.screenHeightDp
    val maxWidth = configuration.screenWidthDp
    val usernameSize = 20 // hardcoded dim.'s
    val dataTextSize = 14
    val iconDim = maxWidth * 0.30 // around a third of the screen width
    val userNameFieldDim = maxWidth * .4


    Box(modifier = with (Modifier) {
        fillMaxSize().background(backgroundBrushBlueYellowTheme)
    })
    {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (userBanner, // the white banner
                userIcon,    // user's profile image
                username,
                usernameSaveIcon,
                followerCount,
                followingCount,
                babCount,
                backProfileBtn, // button to navigate to followers screen
                userDesc,
                descSaveIcon,
                babColumn          // list of THIS user's babs
            ) = createRefs()

            Box(modifier = Modifier // this is the white banner, has the screen's width
                .fillMaxWidth()
                .background(Color.White)
                .height((0.25 * maxHeight).dp)
                .constrainAs(userBanner) {
                    top.linkTo(parent.top, margin = (maxWidth * 0.35).dp)
                    start.linkTo(parent.start)
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
            if (!isLoading) {
                var usernameInput by remember { mutableStateOf(userProfile.user.username) }
                TextField(
                    value = usernameInput,
                    onValueChange = { value -> usernameInput = value },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        focusedTextColor = Color.Black,
                    ),
                    // textAlign = TextAlign.Center,
//                fontSize = usernameSize.sp,
                    modifier = Modifier
                        .width((userNameFieldDim).dp)
                        .border(1.dp, blue, RoundedCornerShape(5.dp))
                        .constrainAs(username) {
                            top.linkTo(userIcon.bottom, margin = 10.dp)
                            absoluteLeft.linkTo(
                                userBanner.absoluteLeft,
                                margin = (maxWidth / 2 - userNameFieldDim / 2).dp
                            )
                        })

                IconButton(
                    onClick = { viewModel.updateName(usernameInput) },
                    modifier = Modifier.constrainAs(usernameSaveIcon) {
                        start.linkTo(username.end, margin = 5.dp)
                        top.linkTo(username.top, margin = 5.dp)
                    }
                ) {
                    Icon(
                        painterResource(id = R.drawable.save_icon),
                        contentDescription = "Trailing Icon",
                        modifier = Modifier
                            .requiredSize(20.dp)
                    )
                }
            }


            // Num. of Followers text
            Text(
                "Followers: ${userProfile?.followerCount}",
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
                "Following: ${userProfile?.followingCount}",
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
                "Babs: ${babs.size}",
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
            // Button to return to profile
            Button(
                onClick = {
                          navController.navigate(NavigationItem.Profile.route)
                },
                modifier = Modifier
                    .height(35.dp)
                    .width(110.dp)
                    .constrainAs(backProfileBtn) {
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
                    text = "Back to Profile",
                    fontSize = 14.sp
                )
            }

            if (!isLoading) {
                var descriptionInput by remember { mutableStateOf(userProfile.description) }
                // user desc.
                TextField(
                    value = descriptionInput,
                    onValueChange = { descriptionInput = it },
                    maxLines = 4,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .height(60.dp)
                        .width(350.dp)
                        .border(1.dp, blue, RoundedCornerShape(5.dp))
                        .constrainAs(userDesc) {
                            top.linkTo(username.bottom, margin = 10.dp)
                            absoluteRight.linkTo(
                                userBanner.absoluteRight,
                                margin = 25.dp
                            )
                        },
                )
                IconButton(
                    onClick = { viewModel.updateDesc(descriptionInput) },
                    modifier = Modifier.constrainAs(descSaveIcon) {
                        top.linkTo(userDesc.top, margin = 5.dp)
                        end.linkTo(userDesc.end, margin = 5.dp)
                    }
                ) {
                    Icon(
                        painterResource(id = R.drawable.save_icon),
                        contentDescription = "Trailing Icon",
                        modifier = Modifier
                            .size(20.dp)
                    )
                }

            }


            // List of Babs
            LazyColumn(userScrollEnabled = true,
                modifier = Modifier
                    .height((maxHeight * 0.5).dp) // half the screen
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
