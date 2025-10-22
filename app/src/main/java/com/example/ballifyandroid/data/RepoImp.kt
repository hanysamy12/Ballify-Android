package com.example.ballifyandroid.data

import com.example.ballifyandroid.data.remote.IRemoteDataSource
import com.example.ballifyandroid.domain.entity.Fixture
import com.example.ballifyandroid.domain.entity.FixtureResponse
import com.example.ballifyandroid.domain.entity.League
import com.example.ballifyandroid.domain.entity.LeagueResponse
import com.example.ballifyandroid.domain.entity.Team
import com.example.ballifyandroid.domain.entity.TeamResponse
import com.example.ballifyandroid.domain.repo.IRepo
import javax.inject.Inject

class RepoImp @Inject constructor(private val remoteDataSource: IRemoteDataSource) : IRepo {
    override suspend fun getSportLeagues(sportName: String): LeagueResponse {
        return remoteDataSource.getSportLeagues(sportName)
    }

    override suspend fun getLeagueFixtures(
        leagueName: String,
        from: String,
        to: String
    ): FixtureResponse {
        return remoteDataSource.getLeagueFixtures(leagueName, from, to)
    }

    override suspend fun getLeagueTeams(leagueName: String, leagueId: Int): TeamResponse {
        return remoteDataSource.getLeagueTeams(leagueName, leagueId)
    }
}