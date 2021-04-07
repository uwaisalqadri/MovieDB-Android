package com.uwaisalqadri.muvi_app.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.uwaisalqadri.muvi_app.domain.model.Cast
import com.uwaisalqadri.muvi_app.domain.model.Movie
import com.uwaisalqadri.muvi_app.domain.model.Video
import com.uwaisalqadri.muvi_app.domain.usecase.MovieUseCase
import com.uwaisalqadri.muvi_app.ui.BaseViewModel
import com.uwaisalqadri.muvi_app.utils.Constants
import com.uwaisalqadri.muvi_app.utils.RxUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import kotlinx.coroutines.launch
import timber.log.Timber
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

    val favoriteState = MutableLiveData<FavoriteState>()

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
                        messageData.value = "Cast is Empty"
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
                        Timber.d("Trailer is Empty")
                    }
                }, this::onError)
        )
    }

    fun checkFavoriteMovie(movieId: String) {
        compositeDisposable.add(
           movieUseCase.getMovieById(movieId)
              .compose(RxUtils.applySingleAsync())
              .subscribe({ result ->
                  if (result.isNotEmpty()) {
                      favoriteState.value = FavMovieDataFound(result)
                  }
              }, this::onError)
        )
    }

    fun saveFavoriteMovie(movie: Movie) {
        viewModelScope.launch {
            movieUseCase.insertMovie(movie)
            favoriteState.value = AddFavorite
        }
    }

    fun removeFavoriteMovie(movie: Movie) {
        viewModelScope.launch {
            movieUseCase.deleteMovie(movie)
            favoriteState.value = RemoveFavorite
        }
    }

    override fun onError(error: Throwable) {
        messageData.value = error.message
        Timber.d(error)
    }
}

sealed class FavoriteState
data class FavMovieDataFound(val movie: List<Movie>) : FavoriteState()
object AddFavorite : FavoriteState()
object RemoveFavorite : FavoriteState()