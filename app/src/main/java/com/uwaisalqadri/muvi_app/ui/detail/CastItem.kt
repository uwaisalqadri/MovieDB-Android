package com.uwaisalqadri.muvi_app.ui.detail

import android.view.View
import com.uwaisalqadri.muvi_app.R
import com.uwaisalqadri.muvi_app.databinding.CastListItemBinding
import com.uwaisalqadri.muvi_app.domain.model.Cast
import com.uwaisalqadri.muvi_app.utils.Constants
import com.uwaisalqadri.muvi_app.utils.loadImage
import com.xwray.groupie.viewbinding.BindableItem

/**
 * Created by Uwais Alqadri on April 06, 2021
 */
class CastItem(
    private val cast: Cast
) : BindableItem<CastListItemBinding>() {

    override fun bind(viewBinding: CastListItemBinding, position: Int) {
        viewBinding.apply {
            cast.profile_path.let { imgCast.loadImage(Constants.URL_IMAGE + it) }
            nameCast.text = cast.name
        }
    }

    override fun getLayout(): Int = R.layout.cast_list_item

    override fun initializeViewBinding(view: View): CastListItemBinding =
        CastListItemBinding.bind(view)
}