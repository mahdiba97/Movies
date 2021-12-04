package com.mahdiba97.movies.feature_movies_info.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahdiba97.movies.core.util.Resource
import com.mahdiba97.movies.feature_movies_info.data.remote.dto.MoreMovieInfo
import com.mahdiba97.movies.feature_movies_info.domain.use_case.GetMoreMovieInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getMoreMovieInfo: GetMoreMovieInfo
) : ViewModel() {
    private var job: Job? = null
    fun getMovieInfo(id: String, data: (MoreMovieInfo) -> Unit) {
        job?.cancel()
        job = viewModelScope.launch {
            val response = getMoreMovieInfo(id)
            response.onEach {
                when (it) {
                    is Resource.Success -> {
                        data(it.data!!)
                    }
                }

            }.launchIn(this)
        }
    }
}