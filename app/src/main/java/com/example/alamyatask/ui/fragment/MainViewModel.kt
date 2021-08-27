package com.example.alamyatask.ui.fragment

import androidx.lifecycle.ViewModel
import com.example.alamyatask.data.network.response.ListItem
import com.example.alamyatask.data.network.response.WetherResponse
import com.example.alamyatask.data.repo.MainRepo
import io.reactivex.Observable

class MainViewModel(private val mainRepo: MainRepo) : ViewModel() {


    fun getWeather(city:String): Observable<WetherResponse> = mainRepo.getWeather(city)


}