package com.codepath.flixster

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class UpcomingMovieActivityDetail : AppCompatActivity()
{
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_detail_upcoming_movie)

		val moviePoster = findViewById<ImageView>(R.id.upcoming_movie_poster_detail)
		val movieTitle = findViewById<TextView>(R.id.upcoming_movie_title_detail)
		val movieReleaseDate = findViewById<TextView>(R.id.upcoming_release_date_detail)
		val movieRating = findViewById<RatingBar>(R.id.upcoming_movie_rating_detail)
		val movieVotes = findViewById<TextView>(R.id.upcoming_movie_votes_detail)
		val movieDescription = findViewById<TextView>(R.id.upcoming_movie_description_detail)
		val upcomingMovieView = findViewById<View>(R.id.upcoming_movie_details)

		val upcomingMovie = intent.getSerializableExtra(UPCOMING_MOVIE_EXTRA) as UpcomingMovie

		movieTitle.text = upcomingMovie.upcomingMovieTitle

		movieReleaseDate.text = "Release Date: ${upcomingMovie.upcomingMovieReleaseDate}"

		movieRating.rating = upcomingMovie.upcomingMovieVoteAverage!!.toFloat()

		Log.d("RATING:", (upcomingMovie.upcomingMovieVoteAverage).toString())

		movieVotes.text = "${upcomingMovie.upcomingMovieVoteCount.toString()} votes"

		Glide.with(this)
			.load(upcomingMovie.moviePosterURL)
			.placeholder(android.R.drawable.ic_menu_gallery)
			.into(moviePoster)

		movieDescription.text = upcomingMovie.upcomingMovieDescription

		upcomingMovieView.setOnClickListener {
			this.supportFinishAfterTransition()
		}
	}
}