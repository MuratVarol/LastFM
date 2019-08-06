package com.varol.lastfm.util.network

import android.content.Context
import com.varol.lastfm.R
import okhttp3.Interceptor
import okhttp3.Response

const val API_KEY_QUERY = "api_key"

/**
 * OkHttp interceptor for providing API_KEY to API requests for every requests.
 */
class RequestInterceptor(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val apiKeyValue = context.getString(R.string.API_KEY)
        var request = chain.request()
        val httpUrl = request.url().newBuilder()
            .addQueryParameter(API_KEY_QUERY, apiKeyValue)
            .build()
        request = request.newBuilder().url(httpUrl).build()

        return chain.proceed(request)
    }
}
