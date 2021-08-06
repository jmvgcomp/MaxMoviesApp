package dev.jmvg.maxmovies.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import dev.jmvg.maxmovies.api.MovieAPI.BASE_IMAGE_URL
import dev.jmvg.maxmovies.databinding.ActivityMovieDetailsBinding
import dev.jmvg.maxmovies.repository.MovieRepository
import java.text.NumberFormat
import java.util.*


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
      binding.movieBudget.text = converterValues(it.budget)
      binding.movieRevenue.text = converterValues(it.revenue)
      binding.movieReleaseDate.text = it.release_date


      binding.movieOverview.text = it.overview

      Glide.with(binding.root)
        .load("${BASE_IMAGE_URL}${it.backdrop_path}")
        .into(binding.moviePoster)



    }



  }

  private fun converterValues(value: Int): String {
    Locale.setDefault(Locale("pt", "BR"))
    val format = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 0
    format.currency = Currency.getInstance("BRL")
    return format.format(value).toString()
  }
}