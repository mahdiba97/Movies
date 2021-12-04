package com.mahdiba97.movies.feature_movies_info.domain.use_case

import com.mahdiba97.movies.core.util.Resource
import com.mahdiba97.movies.feature_movies_info.data.remote.dto.MoreMovieInfo
import com.mahdiba97.movies.feature_movies_info.domain.repository.MovieInfoRepository
import kotlinx.coroutines.flow.Flow

class GetMoreMovieInfo(private val repository: MovieInfoRepository) {

    operator fun invoke(id: String): Flow<Resource<MoreMovieInfo>> = repository.getMoreMovieInfo(id)
}