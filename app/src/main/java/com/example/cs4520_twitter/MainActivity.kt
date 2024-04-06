package com.example.cs4520_twitter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

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
    @Composable
    @Preview(showBackground = true)
    fun UserProfile() {
        val configuration = LocalConfiguration.current
        val maxHeight = configuration.screenHeightDp
        val maxWidth = configuration.screenWidthDp
        val iconDim = maxWidth/3.5 // around a third
        val dummyUsername = "babble_user"
        val dummyFollowerCount = 0
        val dummyFollowingCount = 0
        val dummyLikesCount = 0
        // val dummyPosts // TODO: Post component
        val usernameSize = 20

        Box(modifier = with (Modifier) {
            fillMaxSize().background(backgroundBrushBlueYellowTheme)
        })
        {
            //Stack stuff on here

            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (userBanner, userIcon, username) = createRefs()
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
                    modifier = Modifier.size(iconDim.dp) // hardcoded icon dim.'s, etc.
                        .shadow(elevation = 5.dp,
                            shape = RoundedCornerShape(10.dp))
                        .border(width = 1.dp,
                            color = blue,
                            shape = RoundedCornerShape(10.dp))
                        .constrainAs(userIcon) {
                            // halfway icon height
                            top.linkTo(userBanner.top, margin = (-iconDim/2).dp)
                            // Place icon midway the screen's width
                            absoluteLeft.linkTo(userBanner.absoluteLeft,
                                margin = (maxWidth/2 - iconDim/2).dp)
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
            }

            // place more components of the overall profile screen
        }
    }

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun UserIcon() {
        GlideImage(
            model = "https://m.media-amazon.com/images/I/31YObRg58fL._SY445_SX342_.jpg",
            contentDescription = "",
            modifier = Modifier.size(150.dp).clip(RoundedCornerShape(10))
        )

//        val imageView = ImageView(this)
//
//        Glide.with(this)
//                .load("https://m.media-amazon.com/images/I/31YObRg58fL._SY445_SX342_.jpg") // image url
//                .placeholder(R.drawable.ic_launcher_background)
//                .error(R.drawable.ic_launcher_background)
//                .override(150, 150) // resizing
//                .centerCrop()
//                .into(imageView)
//
//        Image(
//            painter = painterResource(imageView),
//            contentDescription = "",
//            modifier = Modifier.size(150.dp).clip(RoundedCornerShape(10))
//        )

    }

    @Composable
    fun UserBanner() {
        // Should take in inputs such as profile pic, user data
        Box(modifier = with (Modifier) {
            fillMaxWidth().background(Color.White).height(200.dp)
        }) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        }
    }
}
