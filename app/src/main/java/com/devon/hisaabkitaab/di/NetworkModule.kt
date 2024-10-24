package com.devon.hisaabkitaab.di

import com.devon.hisaabkitaab.datasource.remote.ApiService
import com.devon.hisaabkitaab.utils.Constants.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val NetworkModule = module {
    single { getGsonConverterFactory() }
    single { provideOkHttpClient() }
    single { getRetrofitInstance(get(), get()) }
    single { provideAPIClient(get()) }
}

fun getRetrofitInstance(gsonConverterFactory: GsonConverterFactory, client: OkHttpClient): Retrofit
{
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(gsonConverterFactory)
        .build()
}

fun provideOkHttpClient(): OkHttpClient{

    val client = OkHttpClient.Builder()

//    if (BuildConfig.DEBUG) {
//        client.addInterceptor(LoggingInterceptor())
//    }

    client.addInterceptor { chain: Interceptor.Chain ->
        val newRequest = chain.request().newBuilder()
            //.addHeader("Authorization", "Bearer ".plus(AppPreferences.loginData?.token))
            .build()
        chain.proceed(newRequest)
    }

    return client.build()
}

fun provideAPIClient(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

fun getGsonConverterFactory(): GsonConverterFactory {
    return GsonConverterFactory.create()
}