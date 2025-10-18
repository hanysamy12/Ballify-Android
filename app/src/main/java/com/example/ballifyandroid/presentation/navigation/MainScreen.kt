package com.example.ballifyandroid.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ballifyandroid.presentation.screens.favoriteScreen.FavoriteScreen
import com.example.ballifyandroid.presentation.screens.leaguesScreen.LeaguesScreen
import com.example.ballifyandroid.presentation.screens.sportsScreen.SportsScreen

@Preview
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    currentBackStackEntry?.destination?.route

    val startDestination = remember { mutableStateOf(ScreenRoute.Sports.route) }

    val topBarContent = remember { mutableStateOf<@Composable () -> Unit>({}) }
    val snackBarContent = remember { SnackbarHostState() }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { topBarContent.value() },
        snackbarHost = { SnackbarHost(snackBarContent) },
        bottomBar = {
            BottomNavigationBar(navController)
        }) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination.value,
            modifier = Modifier.padding(contentPadding),
            ) {
            composable(ScreenRoute.Sports.route) {
                SportsScreen(setToBar = { topBar -> topBarContent.value = topBar }, navController = navController)
            }
            composable(ScreenRoute.Favourites.route)
            {
                FavoriteScreen()
            }
            composable <ScreenRoute.Leagues>
            {backStackEntry ->
                val leagueName = backStackEntry.arguments?.getString("leagueName")
                LeaguesScreen(leagueName ?:"", setTopBar = {topBarContent.value = it})
            }
        }

    }
}