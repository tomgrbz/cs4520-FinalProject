package com.example.cs4520_twitter.nav

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
//            HomeScreen() TODO("update to the screen components as they are implemented")
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