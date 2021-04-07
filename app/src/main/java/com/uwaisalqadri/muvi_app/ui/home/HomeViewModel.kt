package com.uwaisalqadri.muvi_app.ui.home

import androidx.lifecycle.MutableLiveData
import com.uwaisalqadri.muvi_app.domain.model.Movie
import com.uwaisalqadri.muvi_app.domain.usecase.MovieUseCase
import com.uwaisalqadri.muvi_app.ui.BaseViewModel
import com.uwaisalqadri.muvi_app.utils.Constants
import com.uwaisalqadri.muvi_app.utils.RxUtils
import com.uwaisalqadri.muvi_app.utils.getCurrentDate
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Uwais Alqadri on April 05, 2021
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : BaseViewModel() {

    val discoverMovieData = MutableLiveData<List<Movie>>()
    val popularMovieData = MutableLiveData<List<Movie>>()
    val upComingMovieData = MutableLiveData<List<Movie>>()
    val showProgressbar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    fun getDiscoverMovies(page: Int) {
        showProgressbar.value = true
        compositeDisposable.add(
            movieUseCase.getDiscoverMovies(
                Constants.API_KEY,
                Constants.LANG,
                Constants.SORT_BY,
                false,
                page,
                getCurrentDate("yyyy").toInt()
            ).compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()) {
                        discoverMovieData.value = result
                        showProgressbar.value = false
                    } else {
                        messageData.value = "Data is Empty"
                    }
                }, this::onError)
        )
    }

    fun getPopularMovies(page: Int) {
        showProgressbar.value = true
        compositeDisposable.add(
            movieUseCase.getDiscoverMovies(
                Constants.API_KEY,
                Constants.LANG,
                Constants.SORT_BY,
                false,
                page,
                getCurrentDate("yyyy").toInt()
            ).compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()) {
                        popularMovieData.value = result
                        showProgressbar.value = false
                    } else {
                        messageData.value = "Data is Empty"
                    }
                }, this::onError)
        )
    }

    fun getUpComingMovies(page: Int, year: Int) {
        showProgressbar.value = true
        compositeDisposable.add(
            movieUseCase.getDiscoverMovies(
                Constants.API_KEY,
                Constants.LANG,
                Constants.SORT_BY,
                false,
                page,
                year
            ).compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()) {
                        upComingMovieData.value = result
                        showProgressbar.value = false
                    } else {
                        messageData.value = "Data is Empty"
                    }
                }, this::onError)
        )
    }

    override fun onError(error: Throwable) {
        messageData.value = error.message
    }
}