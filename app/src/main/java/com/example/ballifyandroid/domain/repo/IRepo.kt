package com.example.ballifyandroid.domain.repo

import com.example.ballifyandroid.domain.entity.FixtureResponse
import com.example.ballifyandroid.domain.entity.LeagueResponse
import com.example.ballifyandroid.domain.entity.TeamResponse

interface IRepo {
    suspend fun getSportLeagues(sportName :String): LeagueResponse
    suspend fun getLeagueFixtures(leagueName :String,from :String,to :String): FixtureResponse
    suspend fun getLeagueTeams(leagueName : String,leagueId: Int): TeamResponse
}