package com.example.alamyatask.di

import android.content.Context
import android.util.Log
import com.example.alamyatask.data.network.ApiClient
import com.example.alamyatask.util.Constants
import com.example.alamyatask.util.MyApplication
import com.example.alamyatask.util.checkInternetConnectivity
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit



@Module
class NetworkModule (val context:Context) {



    @Provides
   fun provideOkHttpClient() : OkHttpClient =
       OkHttpClient()
           .newBuilder()
           .cache(cache())
           .addInterceptor(httpLoggingInterceptor()!!)
           .addNetworkInterceptor(networkInterceptor())
           .addInterceptor(offlineInterceptor())
           .writeTimeout(1,TimeUnit.MINUTES)
           .readTimeout(1,TimeUnit.MINUTES)
           .connectTimeout(5,TimeUnit.MINUTES)
           .build()


   @Provides
   fun provideApiClient(client:OkHttpClient):ApiClient =
    Retrofit.Builder()
           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
           .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
           .baseUrl(Constants.BASE_URL)
           .client(client)
           .build().create(ApiClient::class.java)

    private fun cache(): Cache{
        return Cache(
            File(context.cacheDir, "someIdentifier"), Constants.CASHSIZE)

    }

    private fun offlineInterceptor(): Interceptor{
        return object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {

                Log.d(Constants.TAG,
                    "offline interceptor: called."
                )
                var request: Request = chain.request()

                if ( context.checkInternetConnectivity() == false) {
                    val cacheControl: CacheControl = CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS)
                        .build()
                    request = request.newBuilder()
                        .removeHeader(Constants.HEADER_PRAGMA)
                        .removeHeader(Constants.HEADER_CACHE_CONTROL)
                        .cacheControl(cacheControl)
                        .build()
                }
                return chain.proceed(request)
            }
        }
    }


    private fun networkInterceptor(): Interceptor{
        return object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                Log.d(Constants.TAG,
                    "network interceptor: called."
                )
                val response: Response = chain.proceed(chain.request())
                val cacheControl: CacheControl = CacheControl.Builder()
                    .maxAge(5, TimeUnit.SECONDS)
                    .build()
                return response.newBuilder()
                    .removeHeader(Constants.HEADER_PRAGMA)
                    .removeHeader(Constants.HEADER_CACHE_CONTROL)
                    .header(Constants.HEADER_CACHE_CONTROL, cacheControl.toString()
                    )
                    .build()
            }
        }
    }

    private fun httpLoggingInterceptor(): HttpLoggingInterceptor? {

    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
         return logging
    }


}


