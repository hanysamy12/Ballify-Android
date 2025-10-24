package com.example.ballifyandroid.data.remote

import com.example.ballifyandroid.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val apiToken = BuildConfig.API_TOKEN
        val original = chain.request()

        //add query parameter to url
        val url = original.url.newBuilder()
            .addQueryParameter("APIkey", apiToken)
            .build()

        val request = original.newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}