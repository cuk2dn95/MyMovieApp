package com.example.truongquockhanh.mymovieapp.data.remote

import com.example.truongquockhanh.mymovieapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun getInsance(): Retrofit {
        val interceptor = OkHttpClient().apply {
            val interceptor = interceptors()
            interceptor.add(Interceptor { chain: Interceptor.Chain ->
                var request = chain.request()
                val url = request.url().newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build()
                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            })
            interceptor.add(HttpLoggingInterceptor())
        }
        return Retrofit.Builder().client(interceptor)
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}