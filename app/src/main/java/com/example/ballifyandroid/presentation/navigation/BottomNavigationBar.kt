package com.example.ballifyandroid.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ballifyandroid.R


@Composable
fun BottomNavigationBar(navController: NavController) {
    val navigationItems = listOf(
        NavigationItem(
            "Sports",
            ScreenRoute.Sports.route,
            painterResource(R.drawable.sports_balls)
        ),
        NavigationItem(
            "Favorite",
            ScreenRoute.Favourites.route,
            painterResource(R.drawable.favorite_filled)
        ),
    )
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val selectedIndex = remember(currentRoute) {
        navigationItems.indexOfFirst { item -> item.route == currentRoute }
        //navigationItems.indexOfFirst { item -> currentRoute?.startsWith(item.route) == true }
    }.takeIf { it >= 0 } ?: 0
    NavigationBar {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index, onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                    }
                },
                icon = {
                    Icon(painter = item.icon, contentDescription = item.name)
                }, label = {
                    item.name
                })

        }
    }
}

