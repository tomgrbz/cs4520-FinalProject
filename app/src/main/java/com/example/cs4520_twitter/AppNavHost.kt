package com.example.cs4520_twitter

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

enum class Screen {
    FEED,
    LOGIN,
    EDIT_PROFILE,
    PROFILE,
    SEARCH,
    FOLLOWERS,
}
sealed class NavigationItem(val route: String) {
    object Feed : NavigationItem(Screen.FEED.name)
    object Login : NavigationItem(Screen.LOGIN.name)
    object EditProfile : NavigationItem(Screen.LOGIN.name)
    object Profile : NavigationItem(Screen.LOGIN.name)
    object Search : NavigationItem(Screen.LOGIN.name)
    object Followers : NavigationItem(Screen.LOGIN.name)


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
//            HomeScreen()
        }
        composable(NavigationItem.Login.route) {
//            LoginScreen(navController)
        }
        composable(NavigationItem.Profile.route) {
//            LoginScreen(navController)
        }
        composable(NavigationItem.EditProfile.route) {
//            LoginScreen(navController)
        }
        composable(NavigationItem.Search.route) {
//            LoginScreen(navController)
        }
        composable(NavigationItem.Followers.route) {
//            LoginScreen(navController)
        }
    }
}
