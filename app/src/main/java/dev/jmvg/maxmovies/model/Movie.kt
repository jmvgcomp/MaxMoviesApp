package dev.jmvg.maxmovies.model


data class Movie(val title: String,
                 val id: Int,
                 val vote_count: Int,
                 val poster_path: String,
                 val overview: String,
                 val budget: Int,
                 val revenue: Int,
                 val vote_average: Double,
                 val release_date: String,
                 val backdrop_path: String,
                 val runtime: Int?)
