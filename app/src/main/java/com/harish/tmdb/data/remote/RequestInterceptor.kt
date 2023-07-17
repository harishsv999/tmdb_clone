package com.harish.tmdb.data.remote

import com.harish.tmdb.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("accept", "application/json")
        requestBuilder.addHeader("Authorization", "Bearer ${BuildConfig.TMDB_ACCESS}")
        return chain.proceed(requestBuilder.build())
    }
}
