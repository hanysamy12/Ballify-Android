package com.example.ballifyandroid.domain.usecase

import com.example.ballifyandroid.domain.entity.Fixture
import com.example.ballifyandroid.domain.repo.IRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFixturesUseCase @Inject constructor(private val repo: IRepo) {
    operator fun invoke(sportName: String, from: String, to: String, leagueId: Int) =
        flow {
            val response =
                repo.getLeagueFixtures(
                    leagueName = sportName,
                    from = from,
                    to = to,
                    leagueId = leagueId
                )
            emit(response)

        }


}