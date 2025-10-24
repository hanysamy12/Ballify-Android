package com.example.ballifyandroid.data.remote

import com.example.ballifyandroid.domain.entity.Fixture
import com.example.ballifyandroid.domain.entity.FixtureResponse
import com.example.ballifyandroid.domain.entity.League
import com.example.ballifyandroid.domain.entity.LeagueResponse
import com.example.ballifyandroid.domain.entity.Team
import com.example.ballifyandroid.domain.entity.TeamResponse
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val apiService: ApiService): IRemoteDataSource {
    override suspend fun getSportLeagues(sportName: String): LeagueResponse {
        return apiService.getLeagues(sportName)
    }

    override suspend fun getLeagueFixtures(leagueName: String,from : String,to : String,leagueId : Int): FixtureResponse {
        return apiService.getFixtures(sportType = leagueName,from= from, to = to, leagueId = leagueId)
    }

    override suspend fun getLeagueTeams(leagueName: String,leagueId : Int): TeamResponse {
        return apiService.getTeam(sportType = leagueName, leagueId = leagueId)
    }
}