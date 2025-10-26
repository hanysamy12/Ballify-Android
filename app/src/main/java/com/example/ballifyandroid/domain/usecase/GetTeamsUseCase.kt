package com.example.ballifyandroid.domain.usecase

import com.example.ballifyandroid.domain.repo.IRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTeamsUseCase @Inject constructor(val repo: IRepo) {
     operator fun invoke(leagueName: String, leagueId: Int) =
        flow {
            val response = repo.getLeagueTeams(leagueName, leagueId)
            emit(response)
        }

}
