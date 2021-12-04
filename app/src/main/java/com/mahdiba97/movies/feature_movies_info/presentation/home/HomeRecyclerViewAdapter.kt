package com.mahdiba97.movies.feature_movies_info.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mahdiba97.movies.R
import com.mahdiba97.movies.databinding.MovieItemBinding
import com.mahdiba97.movies.feature_movies_info.data.remote.dto.InfoDto

class HomeRecyclerViewAdapter(private val id: (String, String) -> Unit) :
    RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder>() {
    private var items = listOf<InfoDto?>()

    fun setData(query: List<InfoDto?>) {
        items = query
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = MovieItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeRecyclerViewAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewAdapter.ViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            if (item != null) {
                tvTitle.isSelected = true
                tvTitle.text = item.title
                Glide.with(holder.itemView).load(item.image).into(itemPoster)
                holder.binding.root.setOnClickListener {
                    id(item.image ?: "", item.id)
                }
            }
        }
    }

    override fun getItemCount(): Int = items.size
}