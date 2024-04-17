package com.example.cs4520_twitter

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cs4520_twitter.nav.AppNavHost
import com.example.cs4520_twitter.nav.NavBar
import com.example.cs4520_twitter.nav.NavigationItem
import com.example.cs4520_twitter.ui.theme.backgroundBrushBlueYellowTheme

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                var navController = rememberNavController()
                // State of bottomBar, set state to false, if current page route is "car_details"
                val bottomBarState = rememberSaveable { (mutableStateOf(false)) }

                val navBackStackEntry by navController.currentBackStackEntryAsState()

                when (navBackStackEntry?.destination?.route) {
                    NavigationItem.Login.route -> {
                        // Show BottomBar
                        bottomBarState.value = false
                    }
                    NavigationItem.Feed.route -> {
                        bottomBarState.value = true
                    }
                    NavigationItem.Profile.route -> {
                        bottomBarState.value = true
                    }
                    NavigationItem.EditProfile.route -> {
                        bottomBarState.value = true
                    }
                    NavigationItem.Search.route -> {
                        bottomBarState.value = true
                    }
                    NavigationItem.Followers.route -> {
                        bottomBarState.value = true
                    }
                    NavigationItem.AddBab.route -> {
                        bottomBarState.value = true
                    }
                }

                Scaffold(bottomBar = {
                    NavBar(
                        navController = navController,
                        bottomBarState = bottomBarState,
                    )
                }) {
                    Box(modifier = with (Modifier) {
                        fillMaxSize().background(backgroundBrushBlueYellowTheme)
                    })
                    AppNavHost(navController = navController)
                }
            }
        }
    }
}
