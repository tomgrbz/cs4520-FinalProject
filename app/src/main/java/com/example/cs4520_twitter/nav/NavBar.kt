package com.example.cs4520_twitter.nav

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun NavBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Feed,
        BottomNavItem.Search,
        BottomNavItem.AddBab,
        BottomNavItem.Profile
    )

    NavigationBar(
        containerColor = Color.White
    ) {
        Row() {
            items.forEach { screen ->
                NavigationBarItem(
                    // Text that shows bellow the icon
                    label = {
                        Text(text = screen.title, fontSize = 15.sp)
                    },

                    // The icon resource
                    icon = {
                        Icon(
                            painterResource(id = screen.icon),
                            contentDescription = screen.title,
                            Modifier.size(25.dp),
                        )
                    },

                    // Display if the icon it is select or not
                    selected = true,

                    // Always show the label bellow the icon or not
                    alwaysShowLabel = true,

                    // Click listener for the icon
                    onClick = { navController.navigate(screen.screenVal.name) },

                    // Control all the colors of the icon
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.White,
                    )
                )
            }
        }
    }
}

