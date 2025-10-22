package com.example.ballifyandroid.domain.usecase

import com.example.ballifyandroid.domain.repo.IRepo
import javax.inject.Inject

class GetFixturesUseCase @Inject constructor(private val repo : IRepo) {
    suspend operator fun invoke(leagueName : String,from :String,to : String) = repo.getLeagueFixtures(
        leagueName = leagueName,
        from = from,
        to = to
    )
}