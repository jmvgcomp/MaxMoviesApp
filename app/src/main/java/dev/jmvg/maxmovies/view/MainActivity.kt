package dev.jmvg.maxmovies.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.jmvg.maxmovies.adapter.MovieAdapter
import dev.jmvg.maxmovies.databinding.ActivityMainBinding
import dev.jmvg.maxmovies.repository.MovieRepository
import kotlinx.coroutines.DelicateCoroutinesApi

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding;

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    initLayout();
    setupList();

  }

  private fun initLayout(){
    binding = ActivityMainBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)
  }


  private fun setupList(){

    val adapter = MovieAdapter{
      id -> openDetailsActivity(id)
    }
    binding.recyclerViewMoviePopular.adapter = adapter;

    MovieRepository.getPopularMovies(1){
      adapter.setItems(it)
    }
  }

  private fun openDetailsActivity(id: Int){
    val intent = Intent(this, MovieDetails::class.java)
    intent.putExtra("id", id)
    startActivity(intent)
  }
}