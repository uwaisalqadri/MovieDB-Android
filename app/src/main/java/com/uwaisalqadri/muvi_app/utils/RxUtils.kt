package com.uwaisalqadri.muvi_app.utils

import io.reactivex.FlowableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Uwais Alqadri on April 05, 2021
 */
object RxUtils {
    fun <T> applyObservableAsync(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> applySingleAsync(): SingleTransformer<T, T> {
        return SingleTransformer { single ->
            single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> applyObservableMainThread(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable.observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> applyObservableCompute(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}