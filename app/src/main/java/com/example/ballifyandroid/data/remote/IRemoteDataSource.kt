package com.example.ballifyandroid.data.remote

import com.example.ballifyandroid.domain.entity.FixtureResponse
import com.example.ballifyandroid.domain.entity.LeagueResponse
import com.example.ballifyandroid.domain.entity.TeamResponse

interface IRemoteDataSource {
    suspend fun getSportLeagues(sportName: String): LeagueResponse
    suspend fun getLeagueFixtures(leagueName: String, from: String, to: String, leagueId: Int): FixtureResponse
    suspend fun getLeagueTeams(leagueName: String, leagueId: Int): TeamResponse
}

