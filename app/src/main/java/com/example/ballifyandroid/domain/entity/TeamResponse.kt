package com.example.ballifyandroid.domain.entity

import com.google.gson.annotations.SerializedName

data class TeamResponse(
	val result: List<Team?>? = null,
	val success: Int? = null
)

data class Team(
	val coaches: List<CoachesItem?>? = null,
	val players: List<PlayersItem?>? = null,
	@SerializedName("team_key")
	val teamKey: Int? = null,
	@SerializedName("team_name")
	val teamName: String? = null,
	@SerializedName("team_logo")
	val teamLogo: String? = null
)

data class CoachesItem(
	@SerializedName("coach_name")
	val coachName: String? = null
)

data class PlayersItem(
	@SerializedName("player_key")
	val playerKey: Int? = null,
	@SerializedName("player_number")
	val playerNumber: String? = null,
	@SerializedName("player_image")
	val playerImage: String? = null,
	@SerializedName("player_name")
	val playerName: String? = null,
	@SerializedName("player_type")
	val playerType: String? = null
)

