package com.example.ballifyandroid.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenRoute (val route: String){

    @Serializable
    data object Sports: ScreenRoute("allSports")
    @Serializable
    data object Favourites: ScreenRoute("favourites")
    @Serializable
    data class Leagues(val leagueName: String): ScreenRoute("leagues/{leagueName}")
    @Serializable
    data class LeagueDetails(val leagueId : Int?): ScreenRoute("leagueDetails/{leagueId}")
    @Serializable
    data object Teams: ScreenRoute("teams")
    @Serializable
    data object TeamDetails: ScreenRoute("teamDetails")

}