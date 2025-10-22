package com.example.ballifyandroid.domain.usecase

import com.example.ballifyandroid.domain.entity.LeagueResponse
import com.example.ballifyandroid.domain.repo.IRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSportLeaguesUseCase @Inject constructor(private val repo: IRepo) {

     operator fun invoke(sportName: String): Flow<LeagueResponse> =
         flow{ emit(repo.getSportLeagues(sportName)) }


}