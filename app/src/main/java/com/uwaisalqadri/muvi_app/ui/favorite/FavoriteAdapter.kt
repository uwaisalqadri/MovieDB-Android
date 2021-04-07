package com.uwaisalqadri.muvi_app.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.uwaisalqadri.muvi_app.databinding.FavoriteListItemBinding
import com.uwaisalqadri.muvi_app.domain.model.Movie
import com.uwaisalqadri.muvi_app.utils.Constants
import com.uwaisalqadri.muvi_app.utils.formatDate
import com.uwaisalqadri.muvi_app.utils.loadImage

/**
 * Created by Uwais Alqadri on April 07, 2021
 */
class FavoriteAdapter(
    private val onClick: (Movie) -> Unit,
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FavoriteListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private val binding: FavoriteListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                movie.backdrop_path?.let { favMovieImg.loadImage(Constants.URL_IMAGE + it) }
                favMovieTitle.text = movie.title
                favMovieReleased.text = movie.release_date.formatDate("yyyy")
                movie.genres?.map { favMovieGenres.text = it.name }

                btnFavorite.setOnClickListener {
                    onClick(movie)
                }

                root.setOnClickListener {
                    onItemClick(movie)
                }
            }
        }
    }
}











