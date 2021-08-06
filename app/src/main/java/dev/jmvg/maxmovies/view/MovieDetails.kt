package dev.jmvg.maxmovies.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import dev.jmvg.maxmovies.api.MovieAPI.BASE_IMAGE_URL
import dev.jmvg.maxmovies.databinding.ActivityMainBinding
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

    MovieRepository.getMovieById(id){
      binding.movieTitle.text = it.title
      binding.movieRating.text = it.vote_average.toString()
      binding.movieBudget.text = it.budget.toString()
      binding.movieRevenue.text = it.revenue.toString()
      binding.movieReleaseDate.text = it.release_date


      binding.movieOverview.text = it.overview

      Glide.with(binding.root)
        .load("${BASE_IMAGE_URL}${it.backdrop_path}")
        .into(binding.moviePoster)

    }
  }
}