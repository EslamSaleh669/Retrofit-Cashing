package com.example.alamyatask.util

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.net.ConnectivityManager
import com.example.alamyatask.di.*


class MyApplication() : Application() {
    var appComponent: AppComponent? = null


    private var instance: MyApplication? = null

    fun getinsttancee(): MyApplication {
        return instance!!
    }



    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .networkModule(NetworkModule((baseContext)))
            .viewModelFactoryModule(ViewModelFactoryModule()).build()

        if (instance == null) {
            instance = this
        }
    }


    fun hasNetwork(): Boolean {
        return instance!!.isNetworkConnected()
    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting
    }

}