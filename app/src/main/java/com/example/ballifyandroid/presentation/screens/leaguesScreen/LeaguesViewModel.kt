package com.example.ballifyandroid.presentation.screens.leaguesScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ballifyandroid.domain.entity.League
import com.example.ballifyandroid.domain.usecase.GetSportLeaguesUseCase
import com.example.ballifyandroid.presentation.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

private const val TAG = "LeaguesViewModel"
@HiltViewModel
class LeaguesViewModel @Inject constructor(private val getSportLeaguesUseCase: GetSportLeaguesUseCase) : ViewModel() {
    private val _leagues = MutableStateFlow<ApiResponse<List<League>>>(ApiResponse.Loading)
    val leagues: MutableStateFlow<ApiResponse<List<League>>> = _leagues

    suspend fun getSportLeagues(sportName: String){
        try {

            getSportLeaguesUseCase.invoke(sportName)

                .map { it.result.orEmpty().filterNotNull() }
                .catch { _leagues.value = ApiResponse.Failure(it) }
                .collect { _leagues.value = ApiResponse.Success(it) }

        }
        catch (e: Exception){
            _leagues.value = ApiResponse.Failure(e)
            Log.i(TAG, "getSportLeagues: $_leagues")
        }

    }



}