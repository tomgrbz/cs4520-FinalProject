package com.example.cs4520_twitter.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.cs4520_twitter.application.BabbleApplication
import com.example.cs4520_twitter.data_layer.api.Api
import com.example.cs4520_twitter.data_layer.database.BabEntity
import com.example.cs4520_twitter.data_layer.database.dummyImageURL
import com.example.cs4520_twitter.ui.theme.blue
import com.example.cs4520_twitter.ui.theme.darkerPink
import com.example.cs4520_twitter.vms.BabCardViewModel
import com.example.cs4520_twitter.vms.LoginViewModel
import java.text.DateFormat

// Bab card for Bab list. Currently Uses dummy data for the Image URL.
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BabCard(bab : BabEntity) {
    val configuration = LocalConfiguration.current // for screen dimensions
    val maxHeight = configuration.screenHeightDp
    val viewModel: BabCardViewModel = BabCardViewModel(
        Api.babsApiService,
        Api.profilesApiService
    )

    viewModel.fetchLoggedInUserProfile() // fetch user profile
    val userProfile by viewModel.loggedInProfile.collectAsState() // logged in user profile

    // TODO: Do we need the view model have the api re-obtain the bab information?
    // As of now, the card does not update with clicks but I think that's because the BabCard
    // is passed the bab once, which does not update

    // pass the bab count to VM
    viewModel.setBabLikeCount(bab.likes)
    val newBabCount by viewModel.babLikeCount.collectAsState()

    // pass the bab like user list to VM
    viewModel.setUsersThatLiked(bab.likedUserList.toMutableList())
    val newLikeUsersList by viewModel.listUsersThatLiked.collectAsState()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height((maxHeight * 0.2).dp)
            .padding(horizontal = 8.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(corner = CornerSize(15.dp))
    ) {
        Row {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((maxHeight * 0.2).dp)
            ) {
                val (date, username, content, deleteBtn, likes, userIcon, heart) = createRefs()
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height((maxHeight * 0.2).dp)
                ) {
                    val (date, username, content, likes, userIcon, heart) = createRefs()
                    GlideImage( // this is the icon image of the user who babbled
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
                            .constrainAs(userIcon) {
                                // halfway icon height
                                top.linkTo(parent.top, margin = (maxHeight * 0.1 - 65).dp)
                                // Place icon midway the screen's width
                                absoluteLeft.linkTo(
                                    parent.absoluteLeft,
                                    margin = (10).dp
                                )
                            })

                    // display the babbler or user name
                    Text("@" + bab.authorUser.username,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.constrainAs(username) {
                            bottom.linkTo(userIcon.top, margin = (-15).dp)
                            absoluteLeft.linkTo(userIcon.absoluteRight, margin = 5.dp)
                        })

                    // display bab text
                    Text(bab.content, modifier = Modifier.constrainAs(content) {
                        top.linkTo(username.bottom)
                        absoluteLeft.linkTo(userIcon.absoluteRight, margin = 5.dp)
                    })

                    // display number of likes
                    Text("Likes: " + newBabCount.toString(),
                        color = blue,
                        fontSize = 14.sp,
                        modifier = Modifier.constrainAs(likes) {
                            top.linkTo(parent.bottom, margin = (-20).dp)
                            absoluteLeft.linkTo(parent.absoluteLeft, margin = 20.dp)
                        })

                    // Display Date
                    Text("Date: " + android.text.format.DateFormat.format(
                        "MMM. dd, yyyy",
                        bab.date
                    ),
                        color = blue,
                        fontSize = 14.sp,
                        modifier = Modifier.constrainAs(date) {
                            top.linkTo(parent.bottom, margin = (-20).dp)
                            absoluteLeft.linkTo(likes.absoluteRight, margin = 10.dp)
                        })

                    val userLikedBab =
                        newLikeUsersList.contains(userProfile.user.userID) // did the logged user like this bab?
                    val isLiked = remember { mutableStateOf(userLikedBab) } // is bab liked?
                    IconToggleButton( // the like button
                        modifier = Modifier.constrainAs(heart) {
                            top.linkTo(parent.bottom, margin = (-40).dp)
                            absoluteLeft.linkTo(date.absoluteRight, margin = 1.dp)
                        },
                        checked = isLiked.value,
                        onCheckedChange = {
                            isLiked.value = !isLiked.value // change to unlike/like
                            if (isLiked.value) { // update the api
                                viewModel.likeBab(bab.babID)
                            } else {
                                viewModel.unLikeBab(bab.babID)
                            }
                        }) {
                        Icon(
                            imageVector = if (isLiked.value) Icons.Filled.Favorite
                            else Icons.Filled.FavoriteBorder,
                            tint = darkerPink,
                            contentDescription = "Heart/Like Icon",
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
            }
        }
    }
}