package com.example.ballifyandroid.data.remote

import com.example.ballifyandroid.domain.entity.FixtureResponse
import com.example.ballifyandroid.domain.entity.LeagueResponse
import com.example.ballifyandroid.domain.entity.TeamResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("{sportType}")
    suspend fun getLeagues(
        @Path("sportType") sportType: String,
        @Query("met") met: String = "Leagues"
    ): LeagueResponse

    @GET("{sportType}")
    suspend fun getFixtures(
        @Path("sportType") sportType: String,
        @Query("met") met: String = "Fixtures",
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("leagueId") leagueId: Int,
    ): FixtureResponse

    @GET("{sportType}")
    suspend fun getTeam(
        @Path("sportType") sportType: String,
        @Query("met") met: String = "Team",
        @Query("leagueId") leagueId: Int,
    ): TeamResponse
}


