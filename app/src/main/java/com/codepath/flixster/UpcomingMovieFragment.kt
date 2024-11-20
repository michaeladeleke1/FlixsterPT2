package com.codepath.flixster

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONException

private const val API_KEY = BuildConfig.API_KEY
private const val UPCOMING_MOVIE_URL =
	"https://api.themoviedb.org/3/movie/upcoming?api_key=${API_KEY}"

class UpcomingMovieFragment : Fragment()
{
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View?
	{
		val view = inflater.inflate(R.layout.fragment_upcoming_movies_list, container, false)
		val progressBar = view.findViewById<View>(R.id.UpcomingMovieProgressBar) as ProgressBar
		val upcomingRatedMovieRecyclerView =
			view.findViewById<View>(R.id.upcomingMoviesRV) as RecyclerView
		val context = view.context
		upcomingRatedMovieRecyclerView.layoutManager =
			LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
		updateAdapter(progressBar, upcomingRatedMovieRecyclerView, context)
		return view
	}

	private fun updateAdapter(
		progressBar: ProgressBar,
		recyclerView: RecyclerView,
		context: Context
	)
	{
		progressBar.visibility = View.VISIBLE
		val client = AsyncHttpClient()
		client.get(UPCOMING_MOVIE_URL, object : JsonHttpResponseHandler()
		{
			override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?)
			{
				progressBar.visibility = View.INVISIBLE

				try
				{
					val parsedJson = createJson().decodeFromString(
						UpcomingMovieResult.serializer(),
						json?.jsonObject.toString()
					)

					parsedJson.results?.let { list ->
						val upcomingMovieAdapter = UpcomingMovieRecyclerViewAdapter(context, list)
						recyclerView.adapter = upcomingMovieAdapter
					}
				}
				catch (e: JSONException)
				{
					Log.e("UpcomingMovieFragment", "Exception: $e")
				}
			}

			override fun onFailure(
				statusCode: Int,
				headers: Headers?,
				response: String?,
				throwable: Throwable?
			)
			{
				progressBar.visibility = View.INVISIBLE

			}

		})

	}
}