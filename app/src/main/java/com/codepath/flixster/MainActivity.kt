package com.codepath.flixster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.serialization.json.Json

fun createJson() = Json {
	isLenient = true
	ignoreUnknownKeys = true
	useAlternativeNames = false
}

private const val TAG = "MainActivity/"
private const val API_KEY = BuildConfig.API_KEY
private const val TOP_RATED_MOVIE_URL =
	"https://api.themoviedb.org/3/movie/top_rated?api_key=${API_KEY}"
private const val UPCOMING_MOVIE_URL =
	"https://api.themoviedb.org/3/movie/upcoming?api_key=${API_KEY}"


class MainActivity : AppCompatActivity()
{
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val supportFragmentManagerTopRatedMovie = supportFragmentManager
		val fragmentTransactionTopRatedMovie = supportFragmentManagerTopRatedMovie.beginTransaction()
		fragmentTransactionTopRatedMovie.replace(R.id.top_rated_movies_activity, TopRatedMovieFragment(), null).commit()

		val supportFragmentManagerUpcomingMovie = supportFragmentManager
		val fragmentTransactionUpcomingMovie = supportFragmentManagerUpcomingMovie.beginTransaction()
		fragmentTransactionUpcomingMovie.replace(R.id.upcoming_movies_activity, UpcomingMovieFragment(), null).commit()

	}
}