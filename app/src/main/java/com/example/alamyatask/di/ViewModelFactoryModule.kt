package com.example.alamyatask.di

import androidx.lifecycle.ViewModel
import com.example.alamyatask.data.repo.MainRepo
import com.example.alamyatask.ui.fragment.MainViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Named
import androidx.lifecycle.ViewModelProvider.Factory


@Module
class ViewModelFactoryModule  {


    @Provides
    @Named("main")
    fun provideMainViewModel(mainRepo: MainRepo):Factory{

        return object:Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {

                return MainViewModel(mainRepo) as T
            }
        }
    }





}