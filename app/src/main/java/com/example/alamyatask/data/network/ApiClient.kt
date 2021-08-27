package com.example.alamyatask.data.network

import com.example.alamyatask.data.network.response.*
import io.reactivex.Observable
 import retrofit2.http.*

interface ApiClient {

    @GET("forecast")
    fun get_Weather_List(
        @Query("q") cityname: String,
        @Query("appid") token: String

    ): Observable<WetherResponse>


}