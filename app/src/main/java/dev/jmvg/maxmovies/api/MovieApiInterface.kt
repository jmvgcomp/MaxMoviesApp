package dev.jmvg.maxmovies.api

import dev.jmvg.maxmovies.api.TheMoviesAPI.API_KEY
import dev.jmvg.maxmovies.api.TheMoviesAPI.LANGUAGE
import dev.jmvg.maxmovies.model.Movie
import dev.jmvg.maxmovies.model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiInterface {

  @GET("{id}")
  fun getMovieById(
    @Path("id") id: Int,
    @Query("api_key") apiKey: String = API_KEY,
    @Query("language") language: String = LANGUAGE
  ): Call<Movie>

  @GET("popular")
  fun getPopularMovies(
    @Query("api_key") apiKey: String = API_KEY,
    @Query("language") language: String = LANGUAGE,
    @Query("page") page: Int
  ): Call<Movies>


  @GET("top_rated")
  fun getTopRated(
    @Query("api_key") apiKey: String = API_KEY,
    @Query("language") language: String = LANGUAGE,
    @Query("page") page: Int
  ): Call<Movies>

  @GET("upcoming")
  fun getUpcoming(
    @Query("api_key") apiKey: String = API_KEY,
    @Query("language") language: String = LANGUAGE,
    @Query("page") page: Int
  ): Call<Movies>
}