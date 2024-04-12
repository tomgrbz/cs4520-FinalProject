package com.example.cs4520_twitter

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
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

// Steph: These colors/theme are in the theme folder on main,
//        so when I merge, I'll remove this below (77-86)
val darkerPink = Color(0xFFFF7BF1)
private val blue :  Color = Color(0xFF9BAAF8) // 0xFF9BAAF8
private val transition :  Color = Color(0xFFB9C1F1)
private val yellow :  Color = Color(0xFFFFF5E2) // 0xFFFFF5E2
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
val dummyBabList = mutableListOf<Bab>(dummyBab, dummyBab, dummyBab, dummyBab, dummyBab)
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun BabFeedComposable() {
    Box(modifier = with (Modifier) {
        fillMaxSize().background(backgroundBrushBlueYellowTheme)
    })
    {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = { // Contain the feed title
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = Color.White,
                        titleContentColor = Color.Black,
                    ),
                    title = {
                        Text("Babble Feed",
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center)
                    }
                )
            },
            bottomBar = {}, // navigation bar already on main
            floatingActionButton = {} // we won't used this for the feed screen
        ) { innerPadding ->
            LazyColumn( // contains the list of Babs
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(0.dp),
            ) {items(
                count = dummyBabList.size,
                itemContent = { index ->
                    BabCard(dummyBabList[index])
                })
            }
        }
    }
}
val dummyImageURL = "https://m.media-amazon.com/images/I/31YObRg58fL._SY445_SX342_.jpg"
// Bab card for Bab list
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BabCard(bab : Bab) {
    val configuration = LocalConfiguration.current
    val maxHeight = configuration.screenHeightDp
    val maxWidth = configuration.screenWidthDp
    Card(modifier = Modifier
        .fillMaxWidth().height((maxHeight * 0.2).dp)
        .padding(horizontal = 8.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(corner = CornerSize(15.dp))
    ) {
        Row {
            ConstraintLayout (modifier = Modifier.fillMaxWidth().height((maxHeight * 0.2).dp)) {
                val (date, username, content, likes, userIcon, heart) = createRefs()
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
                        .constrainAs(userIcon) {
                            // halfway icon height
                            top.linkTo(parent.top, margin = (maxHeight * 0.1 - 65).dp)
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

                val isLiked = remember { mutableStateOf(false) } // is bab liked?
                IconToggleButton(
                    modifier = Modifier.constrainAs(heart) {
                        top.linkTo(parent.bottom, margin = (-40).dp)
                        absoluteLeft.linkTo(date.absoluteRight, margin = 5.dp)
                    },
                    checked = isLiked.value,
                    onCheckedChange = {
                        isLiked .value= !isLiked.value}) {
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
