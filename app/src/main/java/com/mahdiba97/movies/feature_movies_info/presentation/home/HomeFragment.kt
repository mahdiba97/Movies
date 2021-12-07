package com.mahdiba97.movies.feature_movies_info.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mahdiba97.movies.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {
    private lateinit var binding: HomeFragmentBinding
    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var adapter: HomeRecyclerViewAdapter

    override
    fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        adapter = HomeRecyclerViewAdapter { imageUrl, id ->
            val action = HomeFragmentDirections.actionHomeToDetails(imageUrl, id)
            findNavController().navigate(action)
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                viewModel.searchMovie(p0 ?: "")
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })
        viewModel.snackBarMessage.observe(this, {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        })
        viewModel.isSearching.observe(this, {
            if (it) {
                binding.homePlaceHolderContainer.visibility = View.GONE
            }
        })
        viewModel.isLoading.observe(this, {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            binding.progressLine.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.movieInfoState.observe(this, {
            adapter.setData(it.titles)
            binding.homeRecyclerView.adapter = adapter
            binding.homeRecyclerView.layoutManager = GridLayoutManager(activity, 2)

        })
        return binding.root
    }


}