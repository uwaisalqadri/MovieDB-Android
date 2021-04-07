package com.uwaisalqadri.muvi_app.ui.popular

import android.view.View
import com.uwaisalqadri.muvi_app.R
import com.uwaisalqadri.muvi_app.databinding.SearchListItemBinding
import com.uwaisalqadri.muvi_app.domain.model.Movie
import com.uwaisalqadri.muvi_app.utils.Constants
import com.uwaisalqadri.muvi_app.utils.loadImage
import com.xwray.groupie.viewbinding.BindableItem

/**
 * Created by Uwais Alqadri on April 07, 2021
 */
class SearchItem(
    private val movie: Movie,
    private val onClick: (Movie) -> Unit
) : BindableItem<SearchListItemBinding>() {

    override fun bind(viewBinding: SearchListItemBinding, position: Int) {
        viewBinding.apply {
            movie.poster_path?.let { imgSearchItem.loadImage(Constants.URL_IMAGE + it) }
            tvCastMovie.text = movie.popularity.toString()
            tvTitleMovie.text = movie.title
            movie.genres?.map { tvGenreMovie.text = it.name }

            root.setOnClickListener {
                onClick(movie)
            }
        }
    }

    override fun getLayout(): Int = R.layout.search_list_item

    override fun initializeViewBinding(view: View): SearchListItemBinding {
        return SearchListItemBinding.bind(view)
    }
}