package com.varol.lastfm.remote

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * Singleton class for handling API requests and responses
 */
object ServiceRequestHandler {

    /**
     * Handles all requests and responses with success, errors and exceptions,
     * fills data to DataHolder cascade according to response type.
     * Only Single type supported.
     */
    fun <T> sendRequest(call: Single<T>): Single<DataHolder<T>> {
        return call
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .map<DataHolder<T>> {
                DataHolder.Success(it)
            }
            .onErrorResumeNext { throwable: Throwable ->
                Single.just(
                    DataHolder.Error<T>(throwable)
                ) as Single<out DataHolder<T>>
            }
            .doOnError { t: Throwable -> DataHolder.Error<T>(t) }
    }
}
