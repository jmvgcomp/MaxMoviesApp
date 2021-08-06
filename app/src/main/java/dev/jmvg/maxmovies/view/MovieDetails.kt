package dev.jmvg.maxmovies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import dev.jmvg.maxmovies.api.TheMoviesAPI.BASE_IMAGE
import dev.jmvg.maxmovies.databinding.ActivityMovieDetailsBinding
import dev.jmvg.maxmovies.repository.MovieRepository

class MovieDetails : AppCompatActivity() {

  private lateinit var binding: ActivityMovieDetailsBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)

    val id = intent.getIntExtra("id", -1)

    MovieRepository.getMovieById(id) {
      binding.movieTitle.text = it.title
      binding.movieTagline.text = it.tagline
      binding.movieReleaseDate.text = it.releaseDate
      binding.movieRating.text = it.rating.toString()
      binding.movieBudget.text = it.budget.toString()
      binding.movieRevenue.text = it.revenue.toString()
      binding.movieOverview.text = it.overview

      Glide.with(binding.root)
        .load("${BASE_IMAGE}${it.posterPath}")
    }

  }
}