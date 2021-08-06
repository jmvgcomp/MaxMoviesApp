package dev.jmvg.maxmovies.repository

import dev.jmvg.maxmovies.api.MovieAPI.BASE_URL
import dev.jmvg.maxmovies.api.MovieApiInterface
import dev.jmvg.maxmovies.model.Movie
import dev.jmvg.maxmovies.model.Movies
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRepository {
  private val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

  private val movieApiInterface: MovieApiInterface = retrofit.create(MovieApiInterface::class.java)

  fun getPopularMovies(page: Int, callback: (List<Movie>) -> Unit){
    CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main){
      withContext(Dispatchers.IO){
        movieApiInterface.getPopular(page = page)
          .enqueue(object : Callback<Movies>{
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
              callback(response.body()?.results ?: mutableListOf())
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
              print("Error popular movies " + t.message)
            }
          })
      }
    }
  }
  fun getMovieById(id: Int, callback: (Movie) -> Unit){
    CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main) {
      withContext(Dispatchers.IO){
        movieApiInterface.getMovieById(id).enqueue(object : Callback<Movie>{
          override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
            response.body()?.let(callback)
          }

          override fun onFailure(call: Call<Movie>, t: Throwable) {
            print("Error getMovieById "+ t.message)
          }
        })
      }
    }
  }

  fun getTopRated(page: Int, callback: (List<Movie>) -> Unit){
    CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main) {
      withContext(Dispatchers.IO){
        val callApi = movieApiInterface.getTopRated(page = page)
        callApi.enqueue(object : Callback<Movies> {
          override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
            callback(response.body()?.results ?: mutableListOf())
          }

          override fun onFailure(call: Call<Movies>, t: Throwable) {
            println("Error top rated movies " + t.message)
          }
        })
      }
    }
  }

  fun getUpComing(page: Int, callback: (List<Movie>) -> Unit) {
    CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main){
      withContext(Dispatchers.IO){
        movieApiInterface.getUpcoming(page = page)
          .enqueue(object: Callback<Movies>{
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
              callback(response.body()?.results ?: mutableListOf())
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
              print("Error top upcoming " + t.message)
            }

          })
      }
    }
  }

}