package com.example.alamyatask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.example.alamyatask.ui.fragment.SearchFragment
import com.example.alamyatask.ui.fragment.WeatherListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportFragmentManager.commit {
            replace(R.id.fragmentContainer, SearchFragment())
            addToBackStack("")
        }

    }
}