package com.example.alamyatask.di

import com.example.alamyatask.ui.fragment.WeatherListFragment

import dagger.Component


@Component(modules = [
    NetworkModule::class,
    ViewModelFactoryModule::class
])
interface AppComponent {
    fun inject(weatherListFragment: WeatherListFragment)





}