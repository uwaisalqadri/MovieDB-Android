package com.uwaisalqadri.muvi_app.ui.detail

import androidx.lifecycle.MutableLiveData
import com.uwaisalqadri.muvi_app.domain.model.Cast
import com.uwaisalqadri.muvi_app.domain.model.Movie
import com.uwaisalqadri.muvi_app.domain.model.Video
import com.uwaisalqadri.muvi_app.domain.usecase.MovieUseCase
import com.uwaisalqadri.muvi_app.ui.BaseViewModel
import com.uwaisalqadri.muvi_app.utils.Constants
import com.uwaisalqadri.muvi_app.utils.RxUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Uwais Alqadri on April 06, 2021
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : BaseViewModel() {

    val detailMovieData = MutableLiveData<Movie>()
    val castData = MutableLiveData<List<Cast>>()
    val videoData = MutableLiveData<List<Video>>()
    val showProgressBar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    fun getDetailMovie(movieId: String) {
        showProgressBar.value = true
        compositeDisposable.add(
            movieUseCase.getDetailMovie(movieId, Constants.API_KEY, Constants.LANG)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result != null) {
                        showProgressBar.value = false
                        detailMovieData.value = result
                    } else {
                        showProgressBar.value = false
                        messageData.value = "Data is Empty"
                    }
                }, this::onError)
        )
    }

    fun getDetailCredits(movieId: String) {
        showProgressBar.value = true
        compositeDisposable.add(
            movieUseCase.getDetailCredits(movieId, Constants.API_KEY)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()) {
                        showProgressBar.value = false
                        castData.value = result
                    } else {
                        showProgressBar.value = false
                        messageData.value = "Data is Empty"
                    }
                }, this::onError)
        )
    }

    fun getDetailTrailer(movieId: String) {
        showProgressBar.value = true
        compositeDisposable.add(
            movieUseCase.getDetailTrailer(movieId, Constants.API_KEY)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()) {
                        showProgressBar.value = false
                        videoData.value = result
                    } else {
                        showProgressBar.value = false
                        messageData.value = "Data is Empty"
                    }
                }, this::onError)
        )
    }

    override fun onError(error: Throwable) {
        messageData.value = error.message
    }
}