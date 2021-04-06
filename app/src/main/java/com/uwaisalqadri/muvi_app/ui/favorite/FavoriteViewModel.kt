package com.uwaisalqadri.muvi_app.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uwaisalqadri.muvi_app.domain.usecase.MovieUseCase
import com.uwaisalqadri.muvi_app.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Uwais Alqadri on April 05, 2021
 */
@HiltViewModel
class FavoriteViewModel @Inject constructor(
   private val movieUseCase: MovieUseCase
) : BaseViewModel() {

	val messageData = MutableLiveData<String>()
	val showProgressBar = MutableLiveData<Boolean>()

	override fun onError(error: Throwable) {
		messageData.value = error.message
	}
}