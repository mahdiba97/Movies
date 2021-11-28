package com.mahdiba97.movies.feature_movies_info.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahdiba97.movies.core.util.Resource
import com.mahdiba97.movies.feature_movies_info.data.remote.dto.MovieInfoDto
import com.mahdiba97.movies.feature_movies_info.domain.use_case.GetMovieInfos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMovieInfos: GetMovieInfos
) : ViewModel() {
    private val _searchQuery = MutableLiveData("")
    val searchQuery: LiveData<String> = _searchQuery

    private val _movieInfoState = MutableLiveData<MovieInfoDto>()
    val movieInfoState: LiveData<MovieInfoDto> = _movieInfoState

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isSearching = MutableLiveData<Boolean>()
    val isSearching: LiveData<Boolean> = _isSearching


    private var searchJob: Job? = null

    fun searchMovie(movieName: String) {
        if (movieName.isBlank()) {
            return
        }
        _isSearching.value = true
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            val response = getMovieInfos(movieName)
            response.onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _isLoading.value = true
                    }
                    is Resource.Success -> {
                        _movieInfoState.value = result.data!!
                        _isLoading.value = false
                    }
                    is Resource.Error -> {
                        _isLoading.value = false
                    }
                }
            }.launchIn(this)
        }
    }
}