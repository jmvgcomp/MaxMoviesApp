package dev.jmvg.maxmovies.model

import com.google.gson.annotations.SerializedName


data class Movie(
  val id: Int,
  val title: String,
  val overview: String,
  val popularity: Double,
  val budget: Int,
  val revenue: Long,
  val tagline: String,
  @SerializedName("vote_avarage")
  val rating: Double,
  @SerializedName("poster_path")
  val posterPath: Int,
  @SerializedName("release_date")
  val releaseDate: String,
  val runtime: Int,
  val status: String,
//  val page: Int,
//  val results: List<Movie>
)