package com.mahdiba97.movies.feature_movies_info.data.remote

import com.mahdiba97.movies.feature_movies_info.data.remote.dto.MovieInfoDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface MovieApi {
    @Headers(
        "Accept: application/json",
        "x-rapidapi-host: imdb-internet-movie-database-unofficial.p.rapidapi.com",
        "x-rapidapi-key: d0b2140418msh74545691e9999f1p1e089bjsn404832b4f9b8"
    )
    @GET("search/{movie_name}")
    suspend fun getWordInfo(
        @Path("movie_name") movieName: String
    ): MovieInfoDto

    companion object {
        const val BASE_RUL = "https://imdb-internet-movie-database-unofficial.p.rapidapi.com/"
    }
}