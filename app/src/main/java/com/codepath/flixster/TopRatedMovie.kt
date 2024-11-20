package com.codepath.flixster

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class TopRatedMovieResult(
	@SerialName("results")
	val results: List<TopRatedMovie>?
)

@Keep
@Serializable
data class TopRatedMovie(
	@SerialName("poster_path")
	val topRatedMoviePoster: String?,

	@SerialName("overview")
	val topRatedMovieDescription: String?,

	@SerialName("release_date")
	val topRatedMovieReleaseDate: String?,

	@SerialName("title")
	val topRatedMovieTitle: String?,

	@SerialName("vote_count")
	val topRatedMovieVoteCount: Int?,

	@SerialName("vote_average")
	val topRatedMovieVoteAverage: Float?
) : java.io.Serializable
{
	val moviePosterURL = "https://image.tmdb.org/t/p/w500${topRatedMoviePoster}"
}