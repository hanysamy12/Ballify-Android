package com.example.ballifyandroid.domain.usecase

import com.example.ballifyandroid.domain.repo.IRepo
import javax.inject.Inject

class GetTeamsUseCase @Inject constructor(val repo: IRepo) {
    suspend operator fun invoke(leagueName: String, leagueId: Int) =
        repo.getLeagueTeams(leagueName, leagueId)
}