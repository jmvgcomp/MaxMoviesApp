package dev.jmvg.maxmovies.view

import MoviesAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.jmvg.maxmovies.databinding.ActivityMainBinding
import dev.jmvg.maxmovies.databinding.ActivityMovieDetailsBinding
import dev.jmvg.maxmovies.databinding.ActivityMovieDetailsBindingImpl
import dev.jmvg.maxmovies.repository.MovieRepository

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding;


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initLayout()
    setupList()
  }

  private fun initLayout(){
    this.binding = ActivityMainBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)
  }


  private fun setupList() {
    val adapterPopular = MoviesAdapter(this::openDetailsActivity)
    val adapterTopRated = MoviesAdapter(this::openDetailsActivity)
    val adapterUpcoming = MoviesAdapter(this::openDetailsActivity)


    binding.recyclerViewMoviePopular.adapter = adapterPopular
    binding.recyclerViewMovieTopRated.adapter = adapterTopRated
    binding.recyclerViewMovieUpcoming.adapter = adapterUpcoming

    MovieRepository.getPopularMovies(1, adapterPopular::setItems)
    MovieRepository.getPopularMovies(2, adapterPopular::setItems)

    MovieRepository.getTopRated(1, adapterTopRated::setItems)
    MovieRepository.getTopRated(2, adapterTopRated::setItems)

    MovieRepository.getUpComing(1, adapterUpcoming::setItems)
    MovieRepository.getUpComing(2, adapterUpcoming::setItems)


  }

  private fun openDetailsActivity(id: Int) {
    val intent = Intent(this, MovieDetails::class.java)
    intent.putExtra("id", id)

    startActivity(intent)
  }

}