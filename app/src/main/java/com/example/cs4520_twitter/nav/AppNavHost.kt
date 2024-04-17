package com.example.cs4520_twitter.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cs4520_twitter.composables.SearchScreen

import com.example.cs4520_twitter.composables.AddBabScreen

import com.example.cs4520_twitter.composables.BabFeed

import com.example.cs4520_twitter.composables.LoginScreen
import com.example.cs4520_twitter.composables.UserProfileScreen

enum class Screen {
    FEED,
    LOGIN,
    EDIT_PROFILE,
    PROFILE,
    SEARCH,
    FOLLOWERS,
    ADD_BAB
}
sealed class NavigationItem(val route: String) {
    object Feed : NavigationItem(Screen.FEED.name)
    object Login : NavigationItem(Screen.LOGIN.name)
    object EditProfile : NavigationItem(Screen.EDIT_PROFILE.name)
    object Profile : NavigationItem(Screen.PROFILE.name)
    object Search : NavigationItem(Screen.SEARCH.name)
    object Followers : NavigationItem(Screen.FOLLOWERS.name)
    object AddBab: NavigationItem(Screen.ADD_BAB.name)
}
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Login.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Feed.route) {
            BabFeed() // currently uses dummy data
        }
        composable(NavigationItem.Login.route) {
            LoginScreen(navController) // currently uses dummy data
        }
        composable(NavigationItem.Profile.route) {
            UserProfileScreen(navController = navController) // currently uses dummy data
        }
        composable(NavigationItem.EditProfile.route) {
//            LoginScreen(navController)
        }
        composable(NavigationItem.Search.route) {
            SearchScreen()
        }
        composable(NavigationItem.Followers.route) {
//            LoginScreen(navController)
        }
        composable(NavigationItem.AddBab.route) {
            AddBabScreen() // currently uses dummy data for user card
        }
    }
}
