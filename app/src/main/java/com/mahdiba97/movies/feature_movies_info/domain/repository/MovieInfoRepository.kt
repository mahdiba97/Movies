package com.mahdiba97.movies.feature_movies_info.domain.repository

import com.mahdiba97.movies.core.util.Resource
import com.mahdiba97.movies.feature_movies_info.data.remote.dto.MovieInfoDto
import kotlinx.coroutines.flow.Flow

interface MovieInfoRepository {
    fun getMovieInfo(movieName: String): Flow<Resource<MovieInfoDto>>
}