package com.example.alamyatask.data.repo

import android.content.SharedPreferences
 import com.example.alamyatask.data.network.ApiClient
import com.example.alamyatask.data.network.response.*
import com.example.alamyatask.util.Constants
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

class MainRepo @Inject constructor(
    private val apiClient: ApiClient

) {




    fun getWeather(cityname: String): Observable<WetherResponse> {
        return apiClient.get_Weather_List(cityname,Constants.TOKEN).subscribeOn(Schedulers.io())
    }

}
