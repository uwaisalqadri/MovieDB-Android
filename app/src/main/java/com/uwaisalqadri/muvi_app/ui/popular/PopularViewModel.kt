package com.uwaisalqadri.muvi_app.ui.popular

import androidx.lifecycle.MutableLiveData
import com.uwaisalqadri.muvi_app.domain.model.Movie
import com.uwaisalqadri.muvi_app.domain.usecase.MovieUseCase
import com.uwaisalqadri.muvi_app.ui.BaseViewModel
import com.uwaisalqadri.muvi_app.utils.Constants
import com.uwaisalqadri.muvi_app.utils.RxUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Uwais Alqadri on April 05, 2021
 */
@HiltViewModel
class PopularViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : BaseViewModel() {

    val searchData = MutableLiveData<List<Movie>>()
    val messageData = MutableLiveData<String>()
    val showProgressBar = MutableLiveData<Boolean>()

    fun searchMovies(query: String) {
        showProgressBar.value = true
        compositeDisposable.add(
            movieUseCase.searchMovies(
                Constants.API_KEY,
                Constants.LANG,
                query,
                1)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()) {
                        searchData.value = result
                        showProgressBar.value = false
                    } else {
                        messageData.value = "Movie was not found"
                        showProgressBar.value = false
                    }
                }, this::onError)
        )
    }

    override fun onError(error: Throwable) {
        messageData.value = error.message
        Timber.d(error)
    }
}