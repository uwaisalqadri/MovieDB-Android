package com.uwaisalqadri.muvi_app.ui.home

import android.view.View
import com.uwaisalqadri.muvi_app.R
import com.uwaisalqadri.muvi_app.databinding.SliderHomeItemBinding
import com.uwaisalqadri.muvi_app.domain.model.Movie
import com.uwaisalqadri.muvi_app.utils.Constants
import com.uwaisalqadri.muvi_app.utils.loadImage
import com.xwray.groupie.viewbinding.BindableItem

/**
 * Created by Uwais Alqadri on April 05, 2021
 */
class SliderMovieItem(
    private val movie: Movie
) : BindableItem<SliderHomeItemBinding>() {
    override fun bind(viewBinding: SliderHomeItemBinding, position: Int) {
        viewBinding.apply {
            imgSliderHomeItem.loadImage(Constants.URL_IMAGE + movie.backdrop_path)
        }
    }

    override fun getLayout(): Int = R.layout.slider_home_item

    override fun initializeViewBinding(view: View): SliderHomeItemBinding =
        SliderHomeItemBinding.bind(view)
}