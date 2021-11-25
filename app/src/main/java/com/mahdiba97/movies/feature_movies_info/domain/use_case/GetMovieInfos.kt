package com.mahdiba97.movies.feature_movies_info.domain.use_case

import com.mahdiba97.movies.core.util.Resource
import com.mahdiba97.movies.feature_movies_info.data.remote.dto.MovieInfoDto
import com.mahdiba97.movies.feature_movies_info.domain.repository.MovieInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMovieInfos(private val repository: MovieInfoRepository) {
    operator fun invoke(movieName: String): Flow<Resource<MovieInfoDto>> {
        if (movieName.isBlank()) {
            return flow { }
        }
        return repository.getMovieInfo(movieName)
    }
}