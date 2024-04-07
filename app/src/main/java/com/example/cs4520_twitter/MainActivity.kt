package com.example.cs4520_twitter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalConfiguration
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

class MainActivity : ComponentActivity() {
    private val blue :  androidx.compose.ui.graphics.Color = Color(0xFF9BAAF8) // 0xFF9BAAF8
    private val transition :  androidx.compose.ui.graphics.Color = Color(0xFFB9C1F1)
    private val yellow :  androidx.compose.ui.graphics.Color = Color(0xFFFFF5E2) // 0xFFFFF5E2
    private val backgroundBrushBlueYellowTheme : Brush = Brush.linearGradient(
        colors = listOf(blue, transition, yellow),
        start = Offset(0f, 0f),
        Offset(0f, Float.POSITIVE_INFINITY),
        tileMode = TileMode.Clamp)

    @OptIn(ExperimentalGlideComposeApi::class)
    @Preview(showBackground = true)
    @Composable
    fun UserProfile() {
        val configuration = LocalConfiguration.current
        val maxHeight = configuration.screenHeightDp
        val maxWidth = configuration.screenWidthDp
        val iconDim = maxWidth/3.8 // around a quarter of the screen width
        val dummyUsername = "babble_user"
        val dummyFollowerCount = 0
        val dummyFollowingCount = 0
        val dummyBabCount = 3
        // val dummyPosts // TODO: Bab component
        val usernameSize = 20
        val dataTextSize = 14
        val dummyUser = User("_", dummyUsername, "", "password")
        val dummyBab = Bab(
            id = "0",
            authorUser = dummyUser,
            content = "I made a post!",
            date = SimpleDateFormat("yyyy-MM-dd").parse("2024-04-07"),
            likes = 3,
            likedUserList = mutableListOf<String>())
        val dummyBabList = mutableListOf<Bab>(dummyBab, dummyBab, dummyBab)

        Box(modifier = with (Modifier) {
            fillMaxSize().background(backgroundBrushBlueYellowTheme)
        })
        {
            //Stack stuff on here

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
                    babColumn // babs made by this user
                    ) = createRefs()

                var userDescText by remember { mutableStateOf("Hello!") }
                // var babList: MutableState<List<Bab>> = remember { mutableStateOf() }

                Box(modifier = Modifier // this is the white banner, has the screen's width
                    .fillMaxWidth()
                    .background(Color.White)
                    .height((0.25 * maxHeight).dp)
                    .constrainAs(userBanner) {
                        top.linkTo(parent.top, margin = (maxWidth * 0.35).dp)
                    })

                GlideImage( // this is the icon image
                    model = "https://m.media-amazon.com/images/I/31YObRg58fL._SY445_SX342_.jpg",
                    failure = placeholder(ColorPainter(Color.Red)),
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

                // ------- Place more components of the user banner here
                // Username text
                Text(dummyUsername,
                    // textAlign = TextAlign.Center,
                    fontSize = usernameSize.sp,
                    modifier = Modifier
                    .constrainAs(username) {
                        top.linkTo(userIcon.bottom, margin = (5).dp)
                        absoluteLeft.linkTo(userBanner.absoluteLeft,
                            margin = (maxWidth/2 - (dummyUsername.length
                                    * (usernameSize/2))/2).dp)
                })

                // Followers text
                Text(
                    "Followers: $dummyFollowerCount",
                    color = blue,
                    fontSize = dataTextSize.sp,
                    modifier = Modifier
                        .constrainAs(followerCount) {
                            top.linkTo(userBanner.top, margin = (10).dp)
                            absoluteLeft.linkTo(userBanner.absoluteLeft,
                                margin = 10.dp)
                        })

                // Following text
                Text(
                    "Following: $dummyFollowingCount",
                    color = blue,
                    fontSize = dataTextSize.sp,
                    modifier = Modifier
                        .constrainAs(followingCount) {
                            top.linkTo(followerCount.bottom, margin = (1).dp)
                            absoluteLeft.linkTo(userBanner.absoluteLeft,
                                margin = 10.dp)
                        })

                // Number of Posts text
                Text(
                    "Babs: $dummyBabCount",
                    color = blue,
                    fontSize = dataTextSize.sp,
                    modifier = Modifier
                        .constrainAs(babCount) {
                            top.linkTo(followingCount.bottom, margin = (1).dp)
                            absoluteLeft.linkTo(userBanner.absoluteLeft,
                                margin = 10.dp)
                        })

                Button(
                    onClick = {},
                    modifier = Modifier
                        .height(35.dp)
                        .width(110.dp)
                        .constrainAs(editButton) {
                            bottom.linkTo(userBanner.bottom, margin = 10.dp)
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
                        text = "My followers",
                        fontSize = 14.sp
                    )
                }

                TextField(
                    value = userDescText,
                    onValueChange = { userDescText = it },
                    enabled = false,
                    singleLine = true,
                    modifier = Modifier
                        .height(70.dp)
                        .width(350.dp)
                        .constrainAs(userDesc) {
                            bottom.linkTo(editButton.top, margin = 10.dp)
                            absoluteRight.linkTo(
                                userBanner.absoluteRight,
                                margin = 25.dp
                            )
                        })

                LazyColumn(modifier = Modifier
                    .fillMaxHeight()
                    .width((maxWidth * 0.8).dp)
                    .constrainAs(babColumn) {
                        top.linkTo(userBanner.bottom, margin = 10.dp)
                    }) {
                    items(
                        count = dummyBabList.size,
                        itemContent = { index ->
                            //BabCard(dummyBabList[index])
                        }
                    )
                }
            }

            // place more components of the overall profile screen
        }
    }

    @Composable
    fun AbstractButton() {

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

    @Composable
    fun BabCard(bab : Bab) {
//        Date
//        User
//        Text/Image
//        Likes Amount
//                Image (User)
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(corner = CornerSize(15.dp))) {
            ConstraintLayout {
                val (date, user, text, likes) = createRefs()

            }
        }
    }

    @Composable
    fun UserBanner() {
        // Should take in inputs such as profile pic, user data
        Box(modifier = with (Modifier) {
            fillMaxWidth()
                .background(Color.White)
                .height(200.dp)
        }) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        }
    }
}
