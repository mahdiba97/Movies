package com.mahdiba97.movies.feature_movies_info.di

import com.mahdiba97.movies.feature_movies_info.data.remote.MovieApi
import com.mahdiba97.movies.feature_movies_info.data.remote.ServiceBuilder
import com.mahdiba97.movies.feature_movies_info.data.repository.MovieInfoRepositoryImpl
import com.mahdiba97.movies.feature_movies_info.domain.repository.MovieInfoRepository
import com.mahdiba97.movies.feature_movies_info.domain.use_case.GetMoreMovieInfo
import com.mahdiba97.movies.feature_movies_info.domain.use_case.GetMovieInfos
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieInfosModule {

    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi {
        return ServiceBuilder.buildService(MovieApi::class.java, MovieApi.BASE_RUL)
    }

    @Provides
    @Singleton
    fun provideRepository(api: MovieApi): MovieInfoRepository = MovieInfoRepositoryImpl(api)


    @Provides
    @Singleton
    fun provideGetMovieInfos(repository: MovieInfoRepository) = GetMovieInfos(repository)

    @Provides
    @Singleton
    fun provideGetMoreMovieInfos(repository: MovieInfoRepository) = GetMoreMovieInfo(repository)


}