package com.example.ballifyandroid.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenRoute (val route: String){

    @Serializable
    data object Sports: ScreenRoute("allSports")
    @Serializable
    data object Favourites: ScreenRoute("favourites")
    @Serializable
    data object Leagues: ScreenRoute("leagues")
    @Serializable
    data object LeagueDetails: ScreenRoute("leagueDetails")
    @Serializable
    data object Teams: ScreenRoute("teams")
    @Serializable
    data object TeamDetails: ScreenRoute("teamDetails")

}