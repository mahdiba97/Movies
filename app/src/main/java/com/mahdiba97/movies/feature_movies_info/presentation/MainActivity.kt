package com.mahdiba97.movies.feature_movies_info.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mahdiba97.movies.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}