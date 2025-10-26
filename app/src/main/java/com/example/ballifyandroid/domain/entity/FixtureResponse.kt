package com.example.ballifyandroid.domain.entity

import com.google.gson.annotations.SerializedName

data class FixtureResponse(
	val result: List<Fixture?>? = null,
	val success: Int? = null
)

data class Fixture(
	@SerializedName("away_team_key")
	val awayTeamKey: Int? = null,
	@SerializedName("league_name")
	val leagueName: String? = null,
	@SerializedName("event_home_team")
	val eventHomeTeam: String? = null,
	@SerializedName("home_team_key")
	val homeTeamKey: Int? = null,
	@SerializedName("home_team_logo")
	val eventHomeTeamLogo: String? = null,
	@SerializedName("away_team_logo")
	val eventAwayTeamLogo: String? = null,
	@SerializedName("event_date")
	val eventDate: String? = null,
	@SerializedName("event_final_result")
	val eventFinalResult: String? = null,
	@SerializedName("league_key")
	val leagueKey: Int? = null,
	@SerializedName("event_time")
	val eventTime: String? = null,
	@SerializedName("event_away_team")
	val eventAwayTeam: String? = null,
	@SerializedName("event_status")
	val eventStatus: String? = null
)

