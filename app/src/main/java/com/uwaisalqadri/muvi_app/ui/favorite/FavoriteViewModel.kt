package com.uwaisalqadri.muvi_app.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.muvi_app.domain.model.Movie
import com.uwaisalqadri.muvi_app.domain.usecase.MovieUseCase
import com.uwaisalqadri.muvi_app.ui.BaseViewModel
import com.uwaisalqadri.muvi_app.ui.detail.RemoveFavorite
import com.uwaisalqadri.muvi_app.utils.RxUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Uwais Alqadri on April 05, 2021
 */
@HiltViewModel
class FavoriteViewModel @Inject constructor(
   private val movieUseCase: MovieUseCase
) : BaseViewModel() {

	val messageData = MutableLiveData<String>()
	val favoriteMovieData = MutableLiveData<List<Movie>>()

	fun getFavoriteMovies(title: String) {
		compositeDisposable.add(
			movieUseCase.getFavoriteMovies(title)
				.compose(RxUtils.applySingleAsync())
				.subscribe({ result ->
					if (result.isNotEmpty()) {
						favoriteMovieData.value = result
					}
				}, this::onError)
		)
	}

	fun removeFavoriteMovie(movie: Movie) {
		viewModelScope.launch {
			movieUseCase.deleteMovie(movie)
		}
	}

	override fun onError(error: Throwable) {
		messageData.value = error.message
		Timber.d(error)
	}
}