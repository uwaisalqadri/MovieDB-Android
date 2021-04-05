package com.uwaisalqadri.muvi_app.ui.home

import android.view.View
import com.uwaisalqadri.muvi_app.R
import com.uwaisalqadri.muvi_app.databinding.HomeListItemBinding
import com.uwaisalqadri.muvi_app.domain.model.Movie
import com.uwaisalqadri.muvi_app.utils.Constants
import com.uwaisalqadri.muvi_app.utils.loadImage
import com.xwray.groupie.viewbinding.BindableItem

/**
 * Created by Uwais Alqadri on April 05, 2021
 */
class MovieItem(
    private val movie: Movie
) : BindableItem<HomeListItemBinding>() {

    override fun bind(viewBinding: HomeListItemBinding, position: Int) {
        viewBinding.apply {
            imgHomeItem.loadImage(Constants.URL_IMAGE + movie.poster_path)
        }
    }

    override fun getLayout(): Int = R.layout.home_list_item

    override fun initializeViewBinding(view: View): HomeListItemBinding =
        HomeListItemBinding.bind(view)
}