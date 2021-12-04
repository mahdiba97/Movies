package com.mahdiba97.movies.feature_movies_info.data.repository

import com.mahdiba97.movies.core.util.Resource
import com.mahdiba97.movies.feature_movies_info.data.remote.MovieApi
import com.mahdiba97.movies.feature_movies_info.data.remote.dto.MoreMovieInfo
import com.mahdiba97.movies.feature_movies_info.data.remote.dto.MovieInfoDto
import com.mahdiba97.movies.feature_movies_info.domain.repository.MovieInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class MovieInfoRepositoryImpl(private val api: MovieApi) : MovieInfoRepository {

    override fun getMovieInfo(movieName: String): Flow<Resource<MovieInfoDto>> = flow {
        emit(Resource.Loading())
        try {
            val remoteMovieInfos = api.getWordInfo(movieName)
            emit(Resource.Success(data = remoteMovieInfos))

        } catch (e: HttpException) {
            emit(
                Resource.Error("Oops something went wrong!")
            )
        } catch (e: IOException) {
            emit(
                Resource.Error("Couldn't reach server, check your internet connection.")
            )
        }
    }

    override fun getMoreMovieInfo(id: String): Flow<Resource<MoreMovieInfo>> = flow {
        emit(Resource.Loading())
        try {
            val remoteMoreInfo = api.getMoreInfo(id)
            emit(Resource.Success(data = remoteMoreInfo))

        } catch (e: HttpException) {
            emit(
                Resource.Error("Oops something went wrong!")
            )
        } catch (e: IOException) {
            emit(
                Resource.Error("Couldn't reach server, check your internet connection.")
            )
        }
    }
}