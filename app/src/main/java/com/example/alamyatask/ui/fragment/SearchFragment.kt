package com.example.alamyatask.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.alamyatask.R
import com.example.alamyatask.util.Constants
import com.example.alamyatask.util.makeToast
import kotlinx.android.synthetic.main.searchfragment_layout.*

class SearchFragment :Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.searchfragment_layout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        searchBtn.setOnClickListener {
            val cityname = cityname.text.toString().trim()

            if (cityname.isNotEmpty()){
                (activity as AppCompatActivity).supportFragmentManager.commit {
                    replace(R.id.fragmentContainer,
                        WeatherListFragment().apply {
                            arguments = bundleOf(Pair(Constants.CITY_NAME,cityname))
                        }
                    )
                    addToBackStack("")


                }
            }else{

                activity?.makeToast("City Name Is Required")
            }

        }

    }

}