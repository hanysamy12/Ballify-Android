package com.example.ballifyandroid.domain.entity

import com.google.gson.annotations.SerializedName

data class LeagueResponse(
	val result: List<League?>? = null,
	val success: Int? = null
)

data class League(
	@SerializedName("country_key")
	val countryKey: Int? = null,
	@SerializedName("league_name")
	val leagueName: String? = null,
	@SerializedName("league_logo")
	val leagueLogo: String? = null,
	@SerializedName("country_name")
	val countryName: String? = null,
	@SerializedName("league_key")
	val leagueKey: Int? = null,
	@SerializedName("country_logo")
	val countryLogo: Any? = null
)

