package dev.jmvg.maxmovies.api

import dev.jmvg.maxmovies.api.MovieAPI.API_KEY
import dev.jmvg.maxmovies.api.MovieAPI.LANGUAGE
import dev.jmvg.maxmovies.model.Movie
import dev.jmvg.maxmovies.model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiInterface {

  @GET("popular")
  fun getPopular(
    @Query("api_key") apiKey: String = API_KEY,
    @Query("language") language: String = LANGUAGE,
    @Query("page") page: Int): Call<Movies>


  @GET("{id}")
  fun getMovieById(
    @Path("id") id: Int,
    @Query("api_key") apiKey: String = API_KEY,
    @Query("language") language: String = LANGUAGE
  ): Call<Movie>


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