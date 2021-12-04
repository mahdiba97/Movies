package com.mahdiba97.movies.feature_movies_info.data.remote.dto

data class MoreMovieInfo(
    val cast: List<Cast>,
    val id: String,
    val length: String,
    val plot: String,
    val poster: String,
    val rating: String,
    val rating_votes: String,
    val technical_specs: List<List<String>>,
    val title: String,
    val trailer: Trailer,
    val year: String
)