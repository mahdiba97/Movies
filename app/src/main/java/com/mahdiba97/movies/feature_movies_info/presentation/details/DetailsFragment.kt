package com.mahdiba97.movies.feature_movies_info.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mahdiba97.movies.databinding.DetailsFragmentBinding

class DetailsFragment : Fragment() {
    private val viewModel: DetailsViewModel by activityViewModels()
    private lateinit var binding: DetailsFragmentBinding
    private val args: DetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val imageUrl = args.imageUrl
        val id = args.id
        binding = DetailsFragmentBinding.inflate(inflater, container, false)
        binding.cvBack.setOnClickListener {
            findNavController().navigateUp()
        }
        viewModel.getMovieInfo(id) {
            try {
                Glide.with(requireContext()).load(it.poster).into(binding.itemPoster)
                val builder = StringBuilder()
                binding.tvTitle.text = it.title
                builder.append("Released Date: ${it.year}\n")
                builder.append("Duration: ${it.length}\n")
                builder.append("IMDB Rating: ${it.rating}\n")
                builder.append("${it.rating_votes} Votes")
                binding.tvAllDetails.text = builder.toString()
                binding.tvSummary.text = it.plot
            } catch (e: IllegalStateException) {
            }

        }
        return binding.root
    }


}