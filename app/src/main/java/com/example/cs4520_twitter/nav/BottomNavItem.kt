package com.example.cs4520_twitter.nav

import com.example.cs4520_twitter.R

sealed class BottomNavItem(
    var title: String,
    var icon: Int,
    val screenVal: Screen,
) {
    object Feed :
        BottomNavItem(
            "Feed",
            R.drawable.feed_icon,
            Screen.FEED
        )

    object Search :
        BottomNavItem(
            "Search",
            R.drawable.search_icon,
            Screen.SEARCH,
        )

    object AddBab :
        BottomNavItem(
            "Bab",
            R.drawable.add_icon,
            Screen.ADD_BAB,
        )

    object Profile :
        BottomNavItem(
            "Profile",
            R.drawable.profile_icon,
            Screen.PROFILE,
        )
}
