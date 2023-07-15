package com.harish.tmdb.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("accept", "application/json")
        requestBuilder.addHeader("Authorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYWI1M2RiZmYyMWZlZmYzNzAwYzRlM2UyMWFiZGRmZiIsInN1YiI6IjYxNmM0MGNlZmU2YzE4MDA5MjgyNDMxNSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.KsxmYAJIQjLegZxz9YbOeQrBiZTfcuAWt4h4xeE_4WI")
        return chain.proceed(requestBuilder.build())
    }
}
